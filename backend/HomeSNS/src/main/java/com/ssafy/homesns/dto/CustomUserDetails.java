package com.ssafy.homesns.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{

	private String ID;
	private String PASSWORD;
	// 여러 권한이 있으면, 모두 담아서 set으로 표현?
	private String AUTHORITY;	
	// ENABELD는 그냥 True를 리턴하는 형태로 만들었음 - 계정이 살아있는지에 대한 여부를 표현
	private boolean ENABLED;
	private String NAME;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	public String getUsername() {
		return ID;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	
}
