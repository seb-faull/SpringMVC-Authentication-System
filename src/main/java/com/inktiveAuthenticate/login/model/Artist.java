package com.inktiveAuthenticate.login.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artist_id")
    private int artistId;

    @Column(name = "username")
    @NotEmpty(message = "* Please provide your username")
    private String username;

    @Column(name = "email")
    @Email(message = "* Please provide a valid Email")
    @NotEmpty(message = "* Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 6, message = "* Your password must have at least 6 characters")
    @NotEmpty(message = "* Please provide your password")
    private String password;

    @Column(name = "first_name")
    @NotEmpty(message = "* Please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "* Please provide your last name")
    private String lastName;

    @Column(name = "date_of_birth")
    @NotEmpty(message = "* Please provide your first date of birth")
    private String dateOfBirth;

    @Column(name = "minimum_charge")
    @NotEmpty(message = "* Please provide your first minimum charge")
    private String minimumCharge;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "active_flag")
    private int activeFlag;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist_role", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
