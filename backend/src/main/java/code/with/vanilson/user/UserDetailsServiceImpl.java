package code.with.vanilson.user;

import code.with.vanilson.user.exception.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    /**
     * Retrieves the UserDetails for the given username from the database.
     *
     * @param username The username for which to retrieve user details.
     * @return UserDetails representing the user if found.
     * @throws UsernameNotFoundException if no user with the given username is found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
        log.info("User authenticated successfully: " + username);
        return new CustomUserDetails(user);
    }
}
