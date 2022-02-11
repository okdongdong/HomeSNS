package com.ssafy.homesns.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ssafy.homesns.dao.LoginDao;
import com.ssafy.homesns.dto.CustomUserDetails;
import com.ssafy.homesns.dto.UserDto;

// UsetDetails는 Spring Security에서 사용자 정보를 담는 인터페이스
// VO, Entity 역할
// 인터페이스를 구현하게되면, 오버라이드 되는 메소드가 있고, 공부해야할것

// UserDetailsService 를 구현한 CustomUserDetailsService
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	LoginDao loginDao;

	// DB에서 유저의 권한정보, username, password를 가지고 CustomUserDetails를 return한다
	@Override
	public UserDetails loadUserByUsername(final String username) {
		CustomUserDetails user = loginDao.login(username);
		System.out.println("lodaUserByUsername(Service) 에서 DAO를 갔다와서 찍은 DB에 있는 유저정보");
		System.out.println(user.getUserSeq());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		// 만약 DB에 ID에 해당하는 정보가 없다면? => 해당 부분에 대해서 어떤 리턴이 있어야 하는가?
		// 그냥 다른 처리가 없다면 Unauthorized 에러가 발생한다 ==> 권한정보가 없기 때문인듯
		// 단순하게 권한 없다는 예외보다는 구체적으로 로그인 실패에 대한 예외처리가 되었으면 좋겠다
		
		// 여기서 DB를 갔다올 때, user_cls를 통한 권한에 대한 정보를 받아올 수 있을까?
		// 지금은 그냥 ROLE_USER 권한을 부여했음
		// 유저 정보를 받아서 코드번호에 따라 권한을 부여해줄 수 있을 것
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new org.springframework.security.core.userdetails.User(Integer.toString(user.getUserSeq()),
				user.getPassword(),
				grantedAuthorities);
	}
	
	// null에 대한 예외처리를 생략한 이유 => 애초에 권한이 없다면 해당 메소드가 실행될 수 없음
	public UserDto getUserInfo(String userId) {
		UserDto userDto = loginDao.getUserInfo(userId);
		return userDto;
	}
	
	
//	// lodaUserByUsername 을 Override 해서 로그인 시에 DB에서 유저 정보를 권한정보와 함께 가져오는 역할을 수행
//	@Override	
//	@Transactional
//	public UserDetails loadUserByUsername(final String username) {
//		return userRepository.findOneWithAuthoritiesByUsername(username)
//				.map(user -> createUser(username, user))
//				.orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
//	}
//	// DB정보를 기준으로 해당 유저가 활성화 상태라면, 유저의 권한정보와 username, password를 가지고 user객체를 리턴한다
//	private org.springframework.security.core.userdetails.User createUser(String username, User user) {
//		if (!user.isActivated()) {
//			throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
//		}
//		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//				.map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//				.collect(Collectors.toList());
//		return new org.springframework.security.core.userdetails.User(user.getUsername(),
//				user.getPassword(),
//				grantedAuthorities);
//	}
}