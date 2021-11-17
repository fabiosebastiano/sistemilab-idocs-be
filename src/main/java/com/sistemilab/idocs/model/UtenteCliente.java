package com.sistemilab.idocs.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "utente_cliente")
@Entity
public class UtenteCliente extends PanacheEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente idUtente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Utente getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Utente idUtente) {
        this.idUtente = idUtente;
    }
}