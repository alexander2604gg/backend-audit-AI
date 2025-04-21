package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.AuthRequestDto;
import com.portafolio.auditai.dto.AuthResponseDto;
import com.portafolio.auditai.entity.UserSec;
import com.portafolio.auditai.repository.UserSecRepository;
import com.portafolio.auditai.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserSecRepository userSecRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(AuthRequestDto request) {
        if (userSecRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        UserSec user = new UserSec();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userSecRepository.save(user);
    }

    public AuthResponseDto login(AuthRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDto(token);
    }
}