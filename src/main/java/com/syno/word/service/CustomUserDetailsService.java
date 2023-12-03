package com.syno.word.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// import com.syno.word.model.UserEntity;
// import com.syno.word.repo.UserRepository;

@Service
public class CustomUserDetailsService {//implements org.springframework.security.core.userdetails.UserDetailsService {

    // @Autowired
    // private UserRepository userRepository; // Assuming you have a UserRepository

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // Load user details from your database
    //     UserEntity userEntity = userRepository.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    //     return User.withUsername(userEntity.getUsername())
    //             .password(userEntity.getPassword())
    //             .roles(userEntity.getRoles())
    //             .build();
    // }
}
