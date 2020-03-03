package com.base.security.service;

import com.base.models.User;
import com.base.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String cpf)
            throws UsernameNotFoundException {
    	
        User user = userRepository.findByCpf(cpf)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or CPF : " +cpf)
        );

        return UserPrinciple.build(user);
    }
}