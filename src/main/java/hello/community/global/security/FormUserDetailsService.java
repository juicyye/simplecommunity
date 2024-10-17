package hello.community.global.security;

import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.UserRepository;
import hello.community.global.security.dto.LoginUser;
import hello.community.global.security.dto.PrincipalDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> _user = userRepository.findByEmail(username);
        if (_user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        LoginUser loginUser = new LoginUser(_user.get());
        return new PrincipalDetails(loginUser);
    }
}
