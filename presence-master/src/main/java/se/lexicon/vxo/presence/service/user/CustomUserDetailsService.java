package se.lexicon.vxo.presence.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.security.AppUserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException("Could not find user with email: " + email));
        return new AppUserPrincipal(user);
    }

}
