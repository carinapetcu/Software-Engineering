package group2.cms.config;

import group2.cms.domain.Authority;
import group2.cms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CustomAuth implements AuthenticationProvider {
    private final UserRepository repo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = repo.findByUsername(userName);

        var passwordEncoder = new BCryptPasswordEncoder();
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            var authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(Authority.PCMember.name()));
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
