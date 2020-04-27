package se.lexicon.vxo.presence.dto.app_user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static se.lexicon.vxo.presence.text.ValidationMessages.*;

public class AppUserFormDto {


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
    @NotBlank(message = NOT_EMPTY)
    @Pattern(regexp = "^\\S{1}(?:.){4,}\\S$", flags = Pattern.Flag.CASE_INSENSITIVE, message = BAD_PASSWORD)
    private String password;
    @NotBlank(message = NOT_EMPTY)
    private String passwordConfirmation;
    private String street;
    private String zipCode;
    private String city;
    private String homeNumber;
    private String mobileNumber;
    private String gitHubLink;
    private String linkedInURL;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
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

    public String getLinkedInURL() {
        return linkedInURL;
    }

    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

}
