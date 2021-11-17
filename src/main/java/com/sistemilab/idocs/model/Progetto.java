package com.sistemilab.idocs.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "progetto")
@Entity
public class Progetto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "PROGETTO_DOCUMENTO",
            joinColumns = { @JoinColumn(name = "ID_PROGETTO") },
            inverseJoinColumns = { @JoinColumn(name = "ID_DOCUMENTO") })
    private Set<Documento> documenti = new HashSet<>();

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Documento> getDocumenti() {
        return documenti;
    }

    public void setDocumenti(Set<Documento> documenti) {
        this.documenti = documenti;
    }
}