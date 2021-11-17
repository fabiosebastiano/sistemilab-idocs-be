package com.sistemilab.idocs.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "cliente_progetto")
@Entity
public class ClienteProgetto extends PanacheEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_progetto", nullable = false)
    private Utente idProgetto;


    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Utente getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(Utente idProgetto) {
        this.idProgetto = idProgetto;
    }
}