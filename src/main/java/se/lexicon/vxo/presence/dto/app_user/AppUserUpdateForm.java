package se.lexicon.vxo.presence.dto.app_user;

import javax.validation.constraints.*;

import static se.lexicon.vxo.presence.text.ValidationMessages.*;

/**
 * This class is is used when updating an AppUsers general information
 * like <b>email, firstName, lastName, street, zipCode, city, homeNumber, mobileNumber, gitHubLink and linkedInUrl</b>.
 *
 */
public class AppUserUpdateForm {
    @NotBlank
    private String appUserId;
    @NotBlank(message = NOT_EMPTY)
    @Email(regexp = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$", flags = Pattern.Flag.CASE_INSENSITIVE , message = EMAIL_FORMAT_ERROR)
    private String email;
    @NotBlank(message = NOT_EMPTY)
    @Size(min = 2, max = 255, message = NAME_SIZE_ERROR)
    private String firstName;
    @NotBlank(message = NOT_EMPTY)
    @Size(min = 2, max = 255, message = NAME_SIZE_ERROR)
    private String lastName;
    private String street;
    private String zipCode;
    private String city;
    private String homeNumber;
    private String mobileNumber;
    private String gitHubLink;
    private String linkedInUrl;

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }
}
