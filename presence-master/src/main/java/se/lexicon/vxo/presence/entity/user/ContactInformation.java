package se.lexicon.vxo.presence.entity.user;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static se.lexicon.vxo.presence.text.ApplicationMessages.DEFAULT_NOT_SET;

@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy ="org.hibernate.id.UUIDGenerator"
    )
    private String contactId;
    private String street;
    @Column(length = 20)
    private String zipCode;
    private String city;
    @Column(length = 20)
    private String homeNumber;
    @Column(length = 20)
    private String mobileNumber;
    private String gitHubLink;
    private String linkedInURL;
    private String resumeURL;
    private String personalLetterURL;


    public ContactInformation() {
        contactId = null;
        setStreet(DEFAULT_NOT_SET);
        setZipCode(DEFAULT_NOT_SET);
        setCity(DEFAULT_NOT_SET);
        setHomeNumber(DEFAULT_NOT_SET);
        setMobileNumber(DEFAULT_NOT_SET);
        setGitHubLink(DEFAULT_NOT_SET);
        setLinkedInURL(DEFAULT_NOT_SET);
        setResumeURL(DEFAULT_NOT_SET);
        setPersonalLetterURL(DEFAULT_NOT_SET);
    }

    public String getContactId() {
        return contactId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street != null ? street : DEFAULT_NOT_SET;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode != null ? zipCode : DEFAULT_NOT_SET;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city != null ? city : DEFAULT_NOT_SET;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber != null ? homeNumber : DEFAULT_NOT_SET;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber != null ? mobileNumber : DEFAULT_NOT_SET;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink != null ? gitHubLink : DEFAULT_NOT_SET;
    }

    public String getLinkedInURL() {
        return linkedInURL;
    }

    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL != null ? linkedInURL : DEFAULT_NOT_SET;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL != null ? resumeURL : DEFAULT_NOT_SET;
    }

    public String getPersonalLetterURL() {
        return personalLetterURL;
    }

    public void setPersonalLetterURL(String personalLetterURL) {
        this.personalLetterURL = personalLetterURL != null ? personalLetterURL : DEFAULT_NOT_SET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return Objects.equals(contactId, that.contactId) &&
                Objects.equals(street, that.street) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(homeNumber, that.homeNumber) &&
                Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(gitHubLink, that.gitHubLink) &&
                Objects.equals(linkedInURL, that.linkedInURL) &&
                Objects.equals(resumeURL, that.resumeURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, street, zipCode, city, homeNumber, mobileNumber, gitHubLink, linkedInURL, resumeURL);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactInformation{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", homeNumber='").append(homeNumber).append('\'');
        sb.append(", mobileNumber='").append(mobileNumber).append('\'');
        sb.append(", gitHubLink='").append(gitHubLink).append('\'');
        sb.append(", linkedInURL='").append(linkedInURL).append('\'');
        sb.append(", resumeURL='").append(resumeURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
