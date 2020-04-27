package se.lexicon.vxo.presence.entity.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.vxo.presence.entity.role.AppRole;
import se.lexicon.vxo.presence.entity.role.UserRole;
import se.lexicon.vxo.presence.entity.user.AppUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    public static final String EMAIL = "test@test.com";
    public static final String FIRST_NAME = "Test";
    public static final String LAST_NAME = "Testsson";
    public static final String PASSWORD = "password";
    private AppUser testObject;

    @BeforeEach
    void setUp() {
        testObject = new AppUser(EMAIL, FIRST_NAME, LAST_NAME, PASSWORD);
    }

    @Test
    void testObject_successfully_created() {
        assertNull(testObject.getAppUserId());
        assertEquals(EMAIL, testObject.getEmail());
        assertEquals(FIRST_NAME,testObject.getFirstName());
        assertEquals(LAST_NAME,testObject.getLastName());
        assertEquals(PASSWORD, testObject.getPassword());
        assertTrue(testObject.getRoles().isEmpty());
        assertTrue(testObject.isEnabled());
    }

    @Test
    void given_set_of_1_AppRole_setRoles_success(){
        Collection<AppRole> appRoles = new HashSet<>(Arrays.asList(
                new AppRole(UserRole.APP_USER)
        ));
        testObject.setRoles(appRoles);

        assertEquals(1, testObject.getRoles().size());
    }

    @Test
    void given_copy_equals_true_hashCode_match() {
        AppUser copy = new AppUser(EMAIL, FIRST_NAME, LAST_NAME, PASSWORD);
        assertTrue(testObject.equals(copy));
        assertEquals(copy.hashCode(), testObject.hashCode());
    }

    @Test
    void toString_contains_correct_information() {
        String toString = testObject.toString();
        assertTrue(
    toString.contains("null") &&
                toString.contains(EMAIL) &&
                !toString.contains(PASSWORD) &&
                toString.contains(FIRST_NAME) &&
                toString.contains(LAST_NAME) &&
                toString.contains("true")
        );
    }
}
