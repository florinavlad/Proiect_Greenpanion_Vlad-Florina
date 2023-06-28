package com.florina.greenpanion.service;

import com.florina.greenpanion.dto.request.UserRequest;
//import com.florina.greenpanion.model.ERole;
//import com.florina.greenpanion.model.Role;
import com.florina.greenpanion.model.User;
//import com.florina.greenpanion.repository.RoleRepository;
import com.florina.greenpanion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository usersRepository;
//    private final RoleRepository roleRepository;

    public Optional<User> AddUser(UserRequest user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setState(user.getState());
        newUser.setCity(user.getCity());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setPoints(user.getPoints());
//        Role role = roleRepository.findByName(ERole.USER).orElse(null);
//        if(role == null){
//            role = new Role(ERole.USER);
//            role = roleRepository.save(role);
//        }
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        newUser.setRoles(roles);
        return Optional.of(usersRepository.save(newUser));
    }
    public boolean checkEmailExists(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public List<User> getRanking() {
        return usersRepository.findAllByOrderByPointsDesc();
    }
}
