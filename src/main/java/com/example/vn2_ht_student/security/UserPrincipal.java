package com.example.vn2_ht_student.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
//Thông tin người dùng đã đăng nhập”
@Getter
@AllArgsConstructor
public class UserPrincipal  implements UserDetails {
    private Long userId;
    private  String email;
    private Collection<?extends GrantedAuthority> authorities;

    @Override
    public  String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

}