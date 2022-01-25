package com.starry.sky.infrastructure.oauth.support.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-22
 */
@Slf4j
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    public CustomUsernamePasswordAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                        UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("用户密码错误!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
