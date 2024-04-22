package com.vivabicho.api.models;

import com.vivabicho.api.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotNull
    private String name;
    @Column(length = 120, nullable = false, unique = true)
    @NotNull
    private String email;
    @Column(length = 60, nullable = false)
    @NotNull
    private String password;
    private Boolean admin = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
