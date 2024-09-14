package uz.pdp.projectutils.service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.projectutils.model.User;
import uz.pdp.projectutils.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() ->  new BadCredentialsException("Username or password incorrect"));
        UserDetails admin = org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
        return admin;
    }
}