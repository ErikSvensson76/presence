package se.lexicon.vxo.presence.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.vxo.presence.entity.user.AppUser;

import java.util.Optional;

public interface
AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByEmailIgnoreCase(String email);

}
