package com.zuehlke.securesoftwaredevelopment.config;

import com.zuehlke.securesoftwaredevelopment.domain.Permission;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.repository.UserRepository;
import com.zuehlke.securesoftwaredevelopment.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseAuthenticationProvider.class);

    private final UserRepository userRepository;
    private final PermissionService permissionService;

    private static final String PASSWORD_WRONG_MESSAGE = "Authentication failed for username='%s',password='%s'";

    public DatabaseAuthenticationProvider(UserRepository userRepository, PermissionService permissionService) {
        this.userRepository = userRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        LOG.info("Logging in as {}/{}", username, password);

        boolean success = validCredentials(username, password);
        if (success) {
            User user = userRepository.findByUsername(username);
            List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
            LOG.info("User '{}' has grantedAuthorities '{}'", username, grantedAuthorities);
            return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
        }

        throw new BadCredentialsException(String.format(PASSWORD_WRONG_MESSAGE, username, password));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<Permission> permissions = permissionService.get(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean validCredentials(String username, String password) {
        return userRepository.validCredentials(username, password);
    }
}
