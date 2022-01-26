package com.ssafy.homesns.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

// 토큰 생성, 유효성 검증을 담당한다
@Component
public class TokenProvider implements InitializingBean {

   private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

   private static final String AUTHORITIES_KEY = "auth";

   private final String secret;
   private final long tokenValidityInMilliseconds;
   
   private Key key;

   public TokenProvider() {
      this.secret = Base64.getEncoder().encodeToString("com-ssafy-homesns-ugauga-team-spring-boot-spring-security-very-very-hard-help-me-please".getBytes());
      this.tokenValidityInMilliseconds = 60 * 60 * 24 * 1000;
   }

	// InitializingBean를 implements 하고, afterPropertiesSet 을 Override 한다
	// 이유는, Bean이 생성되고, 의존성 주입을 받은 다음, 주입받은 secret값을 base64로 Decode한 뒤,
    // 키 변수에 할당하기 위함
   @Override
   public void afterPropertiesSet() {
      byte[] keyBytes = Decoders.BASE64.decode(secret);
      this.key = Keys.hmacShaKeyFor(keyBytes);
   }

   // Authentication 객체의 권한정보를 받아서 토큰을 생성하는 함수
   // 파라미터를 받은 다음, tokenValidityInMilliseconds를 사용하는데, 주입받지는 않을 것같다
   // 그리고 생성한 토큰을 return 한다
   // 여기서 HS512 이므로, 아마 64바이트 암호가 필요할듯?
   public String createToken(Authentication authentication) {
      String authorities = authentication.getAuthorities().stream()
         .map(GrantedAuthority::getAuthority)
         .collect(Collectors.joining(","));

      long now = (new Date()).getTime();
      Date validity = new Date(now + this.tokenValidityInMilliseconds);

      return Jwts.builder()
         .setSubject(authentication.getName())
         .claim(AUTHORITIES_KEY, authorities)
         .signWith(key, SignatureAlgorithm.HS512)
         .setExpiration(validity)
         .compact();
   }

   // 토큰을 파라미터로 받아서 토큰에 담긴 정보를 이용해서 Authenticaiton 객체를 리턴하는 메소드
   // 토큰을 받아서 Claim을 만들고, 클레임으로 권한정보를 빼내서 권한정보를 이용해서 유저 객체를 만들고
   // 유저객체, 토큰, 권한정보를 이용해서 최종적으로 Authenticaiton 객체를 리턴
   // 여기서 Authentication 객체는 UsernamePasswordAuthenticaitonToken 이다
   public Authentication getAuthentication(String token) {
      Claims claims = Jwts
              .parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token)
              .getBody();

      Collection<? extends GrantedAuthority> authorities =
         Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

      User principal = new User(claims.getSubject(), "", authorities);

      return new UsernamePasswordAuthenticationToken(principal, token, authorities);
   }

   // 토큰을 파라미터로 받아서 유효성 검사를 할 수 있는 메소드
   // 발생하는 exception을 catch한다
   // 문제가 없다면 true, 문제가 있다면 false를 리턴
   public boolean validateToken(String token) {
      try {
         Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
         return true;
      } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
         logger.info("잘못된 JWT 서명입니다.");
      } catch (ExpiredJwtException e) {
         logger.info("만료된 JWT 토큰입니다.");
      } catch (UnsupportedJwtException e) {
         logger.info("지원되지 않는 JWT 토큰입니다.");
      } catch (IllegalArgumentException e) {
         logger.info("JWT 토큰이 잘못되었습니다.");
      }
      return false;
   }
}
