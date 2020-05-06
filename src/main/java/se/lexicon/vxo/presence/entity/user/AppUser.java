package se.lexicon.vxo.presence.entity.user;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.vxo.presence.entity.role.AppRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String appUserId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "app_user_app_role",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AppRole> roles;
    @OneToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "contact_id")
    private ContactInformation contactInformation;
    @OneToOne(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private ProfileImage profileImage;
    @OneToOne(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Cv cv;
    private boolean enabled;

    public AppUser(String appUserId, String email, String firstName, String lastName, String password, boolean enabled, Collection<AppRole> roles){
        this.appUserId = appUserId;
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEnabled(enabled);
        setRoles(roles);
    }



    public AppUser(String email, String firstName, String lastName, String password){
        this(null, email, firstName,lastName,password, true, null);
    }

    public AppUser() {
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String getAppUserId() {
        return appUserId;
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

    public Set<AppRole> getRoles() {
        if(this.roles == null){
            this.roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        if (roles == null){
            roles = new HashSet<>();
        }
        this.roles = new HashSet<>(roles);
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return enabled == appUser.enabled &&
                Objects.equals(appUserId, appUser.appUserId) &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, email, firstName, lastName, password, enabled);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId='" + appUserId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
