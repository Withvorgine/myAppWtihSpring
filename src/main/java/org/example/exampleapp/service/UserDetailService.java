package org.example.exampleapp.service;

import org.example.exampleapp.model.UserCredentials;
import org.example.exampleapp.model.UserPrincipal;
import org.example.exampleapp.model.response.AuthVerifyResponse;
import org.example.exampleapp.repository.UserCreditentialsRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserCreditentialsRepository userCreditentialsRepository;

    @Lazy
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserDetailService(UserCreditentialsRepository userCreditentialsRepository, @Lazy AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userCreditentialsRepository = userCreditentialsRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserCredentials> byUsername = userCreditentialsRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }else {
            return new UserPrincipal(byUsername.getFirst());
        }

    }

    public void saveUser(UserCredentials user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setUsername(user.getUsername());
        int topByOrderByIdDesc = userCreditentialsRepository.countUsers();
        user.setId(topByOrderByIdDesc + 1);
        userCreditentialsRepository.save(user);
    }

    public String verify(UserCredentials user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticate.isAuthenticated()) {
            String generatedToken = jwtService.generateToken(user);
            AuthVerifyResponse authVerifyResponse = new AuthVerifyResponse();
            authVerifyResponse.setToken(generatedToken);
            authVerifyResponse.setUsername(user.getUsername());
            return authVerifyResponse.getToken();
        }
        return null;
    }
}
