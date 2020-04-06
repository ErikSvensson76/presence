package se.lexicon.vxo.presence.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.data.AppRoleRepository;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.dto.app_user.AppUserUpdateForm;
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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser update(AppUserUpdateForm dto){
        if(dto.getAppUserId() == null) throw new IllegalArgumentException("Entity is not yet stored in the database");
        AppUser appUser = appUserRepository.findById(dto.getAppUserId()).orElseThrow(() -> new AppResourceNotFoundException("Could not find requested resource"));
        if(!appUser.getFirstName().equals(dto.getFirstName())) appUser.setFirstName(dto.getFirstName());
        if(!appUser.getLastName().equals(dto.getLastName())) appUser.setLastName(dto.getFirstName());
        if(!appUser.getEmail().equals(dto.getEmail())) appUser.setEmail(dto.getEmail());
        if(appUser.getContactInformation() != null){
            ContactInformation contactInformation = appUser.getContactInformation();
            if(!contactInformation.getCity().equals(dto.getCity())) contactInformation.setCity(dto.getCity()); //City
            if(!contactInformation.getGitHubLink().equals(dto.getGitHubLink())) contactInformation.setGitHubLink(dto.getGitHubLink()); //Github
            if(!contactInformation.getLinkedInURL().equals(dto.getLinkedInUrl())) contactInformation.setLinkedInURL(dto.getLinkedInUrl()); //Linkedin
            if(!contactInformation.getHomeNumber().equals(dto.getHomeNumber())) contactInformation.setHomeNumber(dto.getHomeNumber()); //home number
            if(!contactInformation.getMobileNumber().equals(dto.getMobileNumber())) contactInformation.setMobileNumber(dto.getMobileNumber()); //mobile number
            if(!contactInformation.getStreet().equals(dto.getStreet())) contactInformation.setStreet(dto.getStreet()); //street
            if(!contactInformation.getZipCode().equals(dto.getZipCode())) contactInformation.setZipCode(dto.getZipCode()); //zip code
            appUser.setContactInformation(contactInformation);
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser save(AppUser appUser){
        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findById(String appUserId){
        return appUserRepository.findById(appUserId);
    }

}
