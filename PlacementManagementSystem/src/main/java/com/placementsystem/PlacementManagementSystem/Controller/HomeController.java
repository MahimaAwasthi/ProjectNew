package com.placementsystem.PlacementManagementSystem.Controller;

import com.placementsystem.PlacementManagementSystem.Entity.JwtRequest;
import com.placementsystem.PlacementManagementSystem.Entity.JwtResponse;
import com.placementsystem.PlacementManagementSystem.Exception.InvalidUserNameAndPassword;
import com.placementsystem.PlacementManagementSystem.Service.UserService;
import com.placementsystem.PlacementManagementSystem.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidUserNameAndPassword("Invalid Credentials");
        }


        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());


        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
