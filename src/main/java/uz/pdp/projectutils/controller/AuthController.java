package uz.pdp.projectutils.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectutils.dto.AuthenticationDto;
import uz.pdp.projectutils.model.User;
import uz.pdp.projectutils.repository.UserRepository;
import uz.pdp.projectutils.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationDto> authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // Generate both access and refresh tokens
        String accessToken = jwtUtil.generateAccessToken(username, authenticate);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // Return the tokens in response
        return ResponseEntity.ok(new AuthenticationDto(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationDto> refresh(@RequestParam("refreshToken") String refreshToken) {
        String username = jwtUtil.getSubject(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(username);

        return ResponseEntity.ok(new AuthenticationDto(newAccessToken, refreshToken)); 
    }
}
