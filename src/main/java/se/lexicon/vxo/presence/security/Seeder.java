package se.lexicon.vxo.presence.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.data.AppRoleRepository;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.entity.AppRole;
import se.lexicon.vxo.presence.entity.AppUser;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
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
        List<AppRole> appRoles = new ArrayList<>(Arrays.asList(
                new AppRole("USER"),
                new AppRole("ADMIN")
        ));

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
        appUserRepository.save(appUser);
    }
}
