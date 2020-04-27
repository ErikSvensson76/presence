package se.lexicon.vxo.presence.service.user;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.dto.app_user.AppUserUpdateForm;
import se.lexicon.vxo.presence.entity.user.AppUser;

import java.io.IOException;
import java.util.Optional;

public interface AppUserService {
    @Transactional(rollbackFor = RuntimeException.class)
    AppUser registerNew(AppUserFormDto appUserFormDto);

    Optional<AppUser> findByEmail(String email);

    @Transactional(rollbackFor = RuntimeException.class)
    AppUser update(AppUserUpdateForm dto);

    AppUser save(AppUser appUser);

    Optional<AppUser> findById(String appUserId);
}
