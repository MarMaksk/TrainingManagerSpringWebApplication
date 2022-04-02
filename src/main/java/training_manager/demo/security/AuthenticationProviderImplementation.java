package training_manager.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class AuthenticationProviderImplementation implements AuthenticationProvider {

    private final UserDetailsServiceImplementation userService;

    @Autowired
    public AuthenticationProviderImplementation(UserDetailsServiceImplementation userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (userService == null)
            throw new InternalAuthenticationServiceException("User service is null");
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = userService.loadUserByUsername(name);
        if (user == null)
            throw new AuthenticationCredentialsNotFoundException("Nu such user was found");
        if (user.getPassword().equals(password))
            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        else
            throw new AuthenticationServiceException("Unable authenticate user due to some problems");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
