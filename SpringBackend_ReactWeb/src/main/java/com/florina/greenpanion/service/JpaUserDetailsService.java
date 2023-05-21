package com.florina.greenpanion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.florina.greenpanion.repository.UserRepository;
import com.florina.greenpanion.model.UserSecurity;

@RequiredArgsConstructor
@Repository
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email).map(UserSecurity::new).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
    }
}
