package se.lexicon.vxo.presence.service.user;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.entity.user.AppUser;

import java.util.Optional;

public interface AppUserService {
    @Transactional(rollbackFor = RuntimeException.class)
    AppUser registerNew(AppUserFormDto appUserFormDto);
    Optional<AppUser> findByEmail(String email);
}
