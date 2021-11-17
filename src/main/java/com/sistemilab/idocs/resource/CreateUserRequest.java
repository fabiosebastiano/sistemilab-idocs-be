package com.sistemilab.idocs.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

public class CreateUserRequest {

    @Size(min = 4, max = 15, message = "username should have size [{min},{max}]")
    @NotBlank(message = "username may not be blank")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]+$", message = "\"username\" should start with a letter and should only accept letters and numbers")
    private String username;

    @NotBlank(message = "password may not be blank")
    private String password;

    @NotBlank(message = "name may not be blank")
    private String nome;

    @NotBlank(message = "surname may not be blank")
    private String cognome;

    @NotBlank(message = "email address may not be blank")
    @Email
    private String mail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
