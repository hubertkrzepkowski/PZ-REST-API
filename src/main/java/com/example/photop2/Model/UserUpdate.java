package com.example.photop2.Model;

import com.example.photop2.Validation.UniqueEmail;
import com.example.photop2.Validation.UniqueLogin;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Entity


public class UserUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "login")
    @NotBlank(message = "login nie może byc pusty")
    @Size(min=3, max=30,message = "Login musi mieć conajmniej 3 znaki")
   // @UniqueLogin
    private String login;
    @Size(min = 8,message = "Hasło musi mieć conajmniej 8 znaków ")
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    @NotBlank(message = "Imie niemoże byc puste")
    @Size(min=2, max=30,message = "Imię musi mieć conajmniej 2 znaki ")
    private String name;
    @NotBlank(message = "Nazwisko nie może być puste ")
    @Size(min=2, max=30,message = " Nazwisko musi mieć conajmniej 2 znaki")
    @Column(name = "surname")
    private String surname;
   // @UniqueEmail
    @NotBlank(message = "Email nie może być pusty")
    @Column(name = "email")
    @Email(message = "Niepoprawny Email")
    private String email;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "description")
    @Size(max=300,message = "Opis jest za długi ")
    private String description;
    @Column(name = "visibility")
    private Boolean visibility;
    @Column(name = "ban_on_adding_photos")
    private Boolean ban_on_adding_photos;
    @Column(name = "ban_to_rate ")
    private Boolean ban_to_rate;
    @Column(name = "ban_account")
    private Boolean ban_account;
    @Column(name = "moderator")
    private Boolean moderator;
    @CreationTimestamp
    @Column(name="date_created_on")
    private Timestamp dateCreatedOn ;
    @Column(name="date_last_login")
    private Timestamp dateLastLogin ;
    //   @Column(name="ip_address_last_login")
    // private String ip_address_last_login ;

    private UserUpdate(){}

    public UserUpdate(Users user){
        this.user_id = user.getUser_id();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.description = user.getDescription();
        this.visibility = user.isVisibility();
        this.ban_on_adding_photos = user.isBan_on_adding_photos();
        this.ban_to_rate = user.isBan_to_rate();
        this.ban_account = user.isBan_account();
        this.moderator = user.isModerator();
        this.dateCreatedOn = user.getDateCreatedOn();
        this.dateLastLogin = user.getDateLastLogin();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean isBan_on_adding_photos() {
        return ban_on_adding_photos;
    }

    public void setBan_on_adding_photos(Boolean ban_on_adding_photos) {
        this.ban_on_adding_photos = ban_on_adding_photos;
    }

    public Boolean isBan_to_rate() {
        return ban_to_rate;
    }

    public void setBan_to_rate(Boolean ban_to_rate) {
        this.ban_to_rate = ban_to_rate;
    }

    public Boolean isBan_account() {
        return ban_account;
    }

    public void setBan_account(Boolean ban_account) {
        this.ban_account = ban_account;
    }

    public Boolean isModerator() {
        return moderator;
    }

    public void setModerator(Boolean moderator) {
        this.moderator = moderator;
    }

    public Timestamp getDateCreatedOn() {
        return dateCreatedOn;
    }

    public void setDateCreatedOn(Timestamp dateCreatedOn) {
        this.dateCreatedOn = dateCreatedOn;
    }

    public Timestamp getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Timestamp dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

  /*  public String getIp_address_last_login() {
        return ip_address_last_login;
    }

    public void setIp_address_last_login(String ip_address_last_login) {
        this.ip_address_last_login = ip_address_last_login;
    }
    */

}