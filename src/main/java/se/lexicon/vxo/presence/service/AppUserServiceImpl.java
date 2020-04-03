package se.lexicon.vxo.presence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.data.AppRoleRepository;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.entity.role.AppRole;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.entity.user.ContactInformation;
import se.lexicon.vxo.presence.entity.role.UserRole;
import se.lexicon.vxo.presence.exception.AppResourceNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private AppRoleRepository appRoleRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser registerNew(AppUserFormDto appUserFormDto){
        AppUser newAppUser = new AppUser(
                appUserFormDto.getEmail(),
                appUserFormDto.getFirstName(),
                appUserFormDto.getLastName(),
                passwordEncoder.encode(appUserFormDto.getPassword())
        );
        Set<AppRole> roleSet = new HashSet<>();
        roleSet.add(appRoleRepository.findAppRoleByRole(UserRole.APP_USER).orElseThrow(() -> new AppResourceNotFoundException("Could not find requested resource")));
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setStreet(appUserFormDto.getStreet());
        contactInformation.setZipCode(appUserFormDto.getZipCode());
        contactInformation.setCity(appUserFormDto.getCity());
        contactInformation.setHomeNumber(appUserFormDto.getHomeNumber());
        contactInformation.setMobileNumber(appUserFormDto.getMobileNumber());
        contactInformation.setGitHubLink(appUserFormDto.getGitHubLink());
        contactInformation.setLinkedInURL(appUserFormDto.getLinkedInURL());

        newAppUser.setRoles(roleSet);
        newAppUser.setContactInformation(contactInformation);
        return appUserRepository.save(newAppUser);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmailIgnoreCase(email);
    }
}
