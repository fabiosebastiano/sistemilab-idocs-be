package com.sistemilab.idocs.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "documento")
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 16)
    private String nome;

    @Column(name = "formato", nullable = false)
    private String formato;

    @Column(name = "approvato", nullable = false)
    private Boolean approvato = false;

    @Column(name = "data_approvazione")
    private LocalDate dataApprovazione;

    public LocalDate getDataApprovazione() {
        return dataApprovazione;
    }

    public void setDataApprovazione(LocalDate dataApprovazione) {
        this.dataApprovazione = dataApprovazione;
    }

    public Boolean getApprovato() {
        return approvato;
    }

    public void setApprovato(Boolean approvato) {
        this.approvato = approvato;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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
}