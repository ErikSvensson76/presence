package se.lexicon.vxo.presence.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.data.AppRoleRepository;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.entity.role.AppRole;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.entity.role.UserRole;
import se.lexicon.vxo.presence.entity.user.ContactInformation;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile(value = "!test")
public class Seeder {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public Seeder(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    @Transactional(rollbackFor = RuntimeException.class)
    public void seed(){
        List<AppRole> appRoles = Arrays.stream(UserRole.values())
                .map(AppRole::new)
                .collect(Collectors.toList());

        appRoles = (List<AppRole>) appRoleRepository.saveAll(appRoles);

        AppUser appUser = new AppUser(
                null,
                "admin_vxo@lexicon.se",
                "Super",
                "Admin",
                passwordEncoder.encode("admin"),
                true,
                appRoles
        );

        AppUser appUser2 = new AppUser(
                null,
                "user_vxo@lexicon.se",
                "Erik",
                "Svensson",
                passwordEncoder.encode("user"),
                true,
                appRoles.stream().filter(appRole -> appRole.getRole() == (UserRole.APP_USER)).collect(Collectors.toSet())
        );

        ContactInformation contactInformation = new ContactInformation();
        ContactInformation userContact = new ContactInformation();
        userContact.setStreet("Friskhetsvägen 2");
        userContact.setZipCode("352 63");
        userContact.setCity("Växjö");
        userContact.setMobileNumber("070-52555297");
        userContact.setGitHubLink("https://github.com/ErikSvensson76");
        userContact.setLinkedInURL("https://www.linkedin.com/in/erik-svensson-474a64184/?originalSubdomain=se");
        appUser.setContactInformation(contactInformation);
        appUser2.setContactInformation(userContact);
        appUserRepository.save(appUser);
        appUserRepository.save(appUser2);
    }
}
