package com.devcolibri.secure.entity;

import com.devcolibri.secure.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "Users")
@Getter
@Setter
public class User implements UserDetails {
    private String name;
    private String email;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)//Вешается перед полем когда поле является коллекцией и её надо поместить в БД, создает всопомгательную таблицу
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))//Указывает название для таблицы и первой колонки
    @Enumerated(EnumType.STRING)//Преобразовывает перечесление в строковое значение
    private Set<Role> roles = new HashSet<>();//Коллекция содержит роли
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }//Проверка является ли пользователь админом
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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