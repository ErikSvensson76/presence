package se.lexicon.vxo.presence.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.vxo.presence.entity.AppRole;
import se.lexicon.vxo.presence.entity.UserRole;

import java.util.Optional;

public interface AppRoleRepository extends CrudRepository<AppRole, String> {
    Optional<AppRole> findAppRoleByRole(UserRole role);
}
