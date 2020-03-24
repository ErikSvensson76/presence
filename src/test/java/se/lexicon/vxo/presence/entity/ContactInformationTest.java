package se.lexicon.vxo.presence.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static se.lexicon.vxo.presence.text.ApplicationMessages.DEFAULT_NOT_SET;

public class ContactInformationTest {

    public static final String STREET = "Friskhetsvägen 2";
    public static final String ZIP_CODE = "35263";
    public static final String CITY = "Växjö";
    public static final String MOBILE_NUMBER = "070-1234567";
    private ContactInformation defaultValues;
    private ContactInformation testObject;

    @BeforeEach
    void setUp() {
        defaultValues = new ContactInformation();
        testObject = new ContactInformation();
        testObject.setStreet(STREET);
        testObject.setZipCode(ZIP_CODE);
        testObject.setCity(CITY);
        testObject.setMobileNumber(MOBILE_NUMBER);
        testObject.setHomeNumber(null);
    }

    @Test
    void default_values_successfully_created() {
        assertNull(defaultValues.getContactId());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getStreet());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getZipCode());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getCity());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getHomeNumber());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getMobileNumber());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getGitHubLink());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getLinkedInURL());
        assertEquals(DEFAULT_NOT_SET, defaultValues.getResumeURL());
    }

    @Test
    void custom_values_successfully_set() {
        assertNull(testObject.getContactId());
        assertEquals(STREET, testObject.getStreet());
        assertEquals(ZIP_CODE, testObject.getZipCode());
        assertEquals(CITY, testObject.getCity());
        assertEquals(DEFAULT_NOT_SET, testObject.getHomeNumber());
        assertEquals(MOBILE_NUMBER, testObject.getMobileNumber());
        assertEquals(DEFAULT_NOT_SET, testObject.getGitHubLink());
        assertEquals(DEFAULT_NOT_SET, testObject.getLinkedInURL());
        assertEquals(DEFAULT_NOT_SET, testObject.getResumeURL());
    }

    @Test
    void given_copy_testObject_equals_true_hashCode_match() {
        ContactInformation copy = new ContactInformation();
        copy.setStreet(STREET);
        copy.setZipCode(ZIP_CODE);
        copy.setCity(CITY);
        copy.setMobileNumber(MOBILE_NUMBER);
        copy.setHomeNumber(null);

        assertTrue(testObject.equals(copy));
        assertEquals(testObject.hashCode(), copy.hashCode());
    }

    @Test
    void toString_contains_correct_information() {
        String toString = testObject.toString();
        assertTrue(
                toString.contains(STREET) &&
                        toString.contains(ZIP_CODE) &&
                        toString.contains(CITY) &&
                        toString.contains(MOBILE_NUMBER) &&
                        toString.contains(DEFAULT_NOT_SET)
        );
    }
}
