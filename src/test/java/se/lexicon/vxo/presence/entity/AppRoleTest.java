package se.lexicon.vxo.presence.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppRoleTest {
    public static final String ROLE = "TEST_ROLE";
    private AppRole testObject;

    @BeforeEach
    void setUp() {
        testObject = new AppRole(ROLE);
    }

    @Test
    void testObject_successfully_created() {
        assertNull(testObject.getRoleId());
        assertEquals(ROLE, testObject.getRole());
    }

    @Test
    void given_copy_equals_true_hashCode_match() {
        AppRole copy = new AppRole(ROLE);
        assertTrue(testObject.equals(copy));
    }

    @Test
    void toString_contains_correct_info() {
        String toString = testObject.toString();
        assertTrue(
                toString.contains("null") &&
                        toString.contains(ROLE)
        );
    }
}
