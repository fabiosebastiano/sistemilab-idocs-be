package com.sistemilab.idocs.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "utente", indexes = {
        @Index(name = "utente_mail_key", columnList = "mail", unique = true),
        @Index(name = "utente_username_key", columnList = "username", unique = true)
})
@Entity
public class Utente extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "UTENTE_CLIENTE",
            joinColumns = { @JoinColumn(name = "ID_UTENTE") },
            inverseJoinColumns = { @JoinColumn(name = "ID_CLIENTE") })
    private Set<Cliente> clienti = new HashSet<>();

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "mail", nullable = false, length = 16)
    private String mail;

    @Column(name = "nome", nullable = false, length = 16)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 16)
    private String cognome;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(Set<Cliente> clienti) {
        this.clienti = clienti;
    }
}