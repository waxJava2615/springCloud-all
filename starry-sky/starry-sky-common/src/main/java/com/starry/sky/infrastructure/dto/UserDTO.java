package com.starry.sky.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-26
 */
@Getter
@Setter
public class UserDTO {


    private Long id;
    private String logo;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserDTO(Long id, String logo, String username, String password, Set<GrantedAuthority> authorities,
                   boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                   boolean enabled) {
        this.id = id;
        this.logo = logo;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }


}
