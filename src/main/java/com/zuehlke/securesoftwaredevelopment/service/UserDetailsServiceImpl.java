package com.zuehlke.securesoftwaredevelopment.service;

import com.zuehlke.securesoftwaredevelopment.domain.Permission;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final PermissionService permissionService;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(PermissionService permissionService, UserRepository userRepository) {
        this.permissionService = permissionService;
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        List<Permission> permissions = permissionService.get(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        user.setAuthorities(authorities);

        LOG.info("User '{}' has authorities '{}'", username, user.getAuthorities());
        return user;
    }
}
