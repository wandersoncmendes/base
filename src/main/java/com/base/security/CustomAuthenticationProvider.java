package com.base.security;

import com.base.models.User;
import com.base.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        User user = userRepository.findByEmail(username).orElse(null);
        if ((user == null) || !user.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(
                user, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Authentication authenticationUser(UsernamePasswordAuthenticationToken user) {
        String username = user.getName();
        String password = user.getCredentials().toString();

        User userFound = userRepository.findByCpf(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or CPF" + user.getName())
                );

        if (!encoder.matches(password, userFound.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(
                username, password, new ArrayList<>());
    }

}