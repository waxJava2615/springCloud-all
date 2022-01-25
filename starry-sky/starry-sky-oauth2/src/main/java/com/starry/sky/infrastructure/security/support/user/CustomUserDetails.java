package com.starry.sky.infrastructure.security.support.user;

import com.starry.sky.infrastructure.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author wax
 * @description: 后台认证
 * @date 2021-10-09
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserDTO userDetailDTO;

    public CustomUserDetails(UserDTO userDetailDTO) {
       this.userDetailDTO = userDetailDTO;
    }

    public Long getId(){
        return userDetailDTO.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetailDTO.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetailDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetailDTO.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetailDTO.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetailDTO.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetailDTO.isEnabled();
    }
}
