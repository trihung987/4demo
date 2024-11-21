package me.trihung.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import me.trihung.entity.UserInfo;
import me.trihung.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService{
	
	@Autowired
	UserInfoRepository repository;
	
	public UserInfoService(UserInfoRepository userInfoRepository) {
		this.repository = userInfoRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userinfo = repository.findByName(username);
		return userinfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
	}

}
