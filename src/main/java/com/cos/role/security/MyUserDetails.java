package com.cos.role.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.secure.spi.GrantedPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.role.model.User;
import com.cos.role.model.UserRole;

// Principal (접근 주체) = 세션처럼 사용 = Spring Security Context 에 보관됨
public class MyUserDetails implements UserDetails{

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 계정의 비밀번호를 리턴한다.
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 계정의 이름을 리턴한다.
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (화)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		List<UserRole> roles = user.getRoles();
		for(UserRole ur : roles) {
			System.out.println(ur.getRole().getRole());
			collectors.add(new SimpleGrantedAuthority("ROLE_"+ur.getRole().getRole()));
		}
				
		return collectors;
	}
	
}
