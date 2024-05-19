package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to save a new user
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirst_name(userDto.getFirstName());
        user.setLast_name(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // Encrypt the password using Spring Security's password encoder
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Find the ROLE_USER role or create it if it doesn't exist
        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    // Method to find a user by email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to retrieve all users and map them to DTOs
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    // Method to map User entity to UserDto
    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setFirstName(user.getFirst_name());
        userDto.setLastName(user.getLast_name());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    // Method to check if ROLE_USER role exists, create it if not, and return it
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
