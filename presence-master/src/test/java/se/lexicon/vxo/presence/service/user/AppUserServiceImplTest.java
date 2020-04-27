package se.lexicon.vxo.presence.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.dto.app_user.AppUserUpdateForm;
import se.lexicon.vxo.presence.entity.role.AppRole;
import se.lexicon.vxo.presence.entity.role.UserRole;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.entity.user.ContactInformation;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class AppUserServiceImplTest {

    @Autowired private AppUserService testObject;
    @Autowired private TestEntityManager em;
    private AppUser testUser;


    @BeforeEach
    void setUp() {
        Set<AppRole> roles = Arrays.stream(UserRole.values())
                .map(userRole -> em.persist(new AppRole(userRole)))
                .collect(Collectors.toSet());
        ContactInformation testUserContact = new ContactInformation();
        testUserContact.setStreet("Test street");
        testUserContact.setZipCode("12345");
        testUserContact.setCity("Test ville");
        testUserContact.setMobileNumber("0701234567");
        testUserContact.setHomeNumber("0470123456");
        testUser = em.persist(new AppUser("test@test.com","Test","Testsson","password123"));
        testUser.setContactInformation(testUserContact);
        testUser.setRoles(roles);
        testUser = testObject.save(testUser);
    }

    @Test
    void given_email_findByEmail_should_return_testUser() {
        String email = "test@test.com";
        Optional<AppUser> result = testObject.findByEmail(email);

        assertTrue(result.isPresent());
    }

    @Test
    void given_appUserId_findById_should_return_testUser() {
        String appUserId = testUser.getAppUserId();
        Optional<AppUser> result = testObject.findById(appUserId);
        assertTrue(result.isPresent());
    }

    @Test
    void given_appUserFormDto_registerNew_successfully_return_persisted_AppUser() {
        AppUserFormDto appUserFormDto = new AppUserFormDto();
        appUserFormDto.setEmail("erik@gmail.com");
        appUserFormDto.setFirstName("Erik");
        appUserFormDto.setLastName("Testsson");
        appUserFormDto.setPassword("testing123");

        AppUser result = testObject.registerNew(appUserFormDto);

        assertNotNull(result);
        assertNotNull(result.getAppUserId());
        assertNotNull(result.getContactInformation());
        assertTrue(result.getRoles().stream().allMatch(appRole -> appRole.getRole() == UserRole.APP_USER));
    }

    @Test
    void given_appUserUpdateForm_update_successfully_updates_and_return_updated_AppUser() {
        AppUserUpdateForm appUserUpdateForm = new AppUserUpdateForm();
        appUserUpdateForm.setAppUserId(testUser.getAppUserId());
        appUserUpdateForm.setFirstName(testUser.getFirstName());
        appUserUpdateForm.setLastName(testUser.getLastName());
        appUserUpdateForm.setEmail("test@test.org");
        appUserUpdateForm.setStreet(testUser.getContactInformation().getStreet());
        appUserUpdateForm.setZipCode(testUser.getContactInformation().getZipCode());
        appUserUpdateForm.setCity(testUser.getContactInformation().getCity());
        appUserUpdateForm.setHomeNumber(testUser.getContactInformation().getHomeNumber());
        appUserUpdateForm.setMobileNumber(testUser.getContactInformation().getMobileNumber());
        appUserUpdateForm.setGitHubLink("test");
        appUserUpdateForm.setLinkedInUrl("test");

        AppUser result = testObject.update(appUserUpdateForm);

        assertNotNull(result);
        assertEquals("test@test.org", result.getEmail());
        assertEquals("test", result.getContactInformation().getGitHubLink());
        assertEquals("test", result.getContactInformation().getLinkedInURL());
    }
}
