package se.lexicon.vxo.presence.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.lexicon.vxo.presence.entity.AppRole;
import se.lexicon.vxo.presence.entity.AppUser;

import java.util.Collection;
import java.util.HashSet;

public class AppUserPrincipal implements UserDetails {

    private AppUser appUser;
    private Collection<GrantedAuthority> authorities;

    public AppUserPrincipal(AppUser appUser) {
        this.appUser = appUser;
        Collection<GrantedAuthority> authoritySet = new HashSet<>();
        for(AppRole role : appUser.getRoles()){
            authoritySet.add(new SimpleGrantedAuthority(role.getRole()));
        }
        authorities = authoritySet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public AppUser getAppUser(){
        return appUser;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.isEnabled();
    }
}
