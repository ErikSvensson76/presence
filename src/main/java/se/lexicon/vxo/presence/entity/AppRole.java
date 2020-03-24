package se.lexicon.vxo.presence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String roleId;
    @Column(unique = true)
    private String role;

    public AppRole(String role){
        this.role = role;
    }

    public AppRole() {
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return roleId == appRole.roleId &&
                Objects.equals(role, appRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
