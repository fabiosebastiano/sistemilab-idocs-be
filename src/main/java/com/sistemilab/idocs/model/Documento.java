package com.sistemilab.idocs.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "documento")
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estensione", nullable = false, length = 16)
    private String estensione;

    @Column(name = "dimensione", nullable = false)
    private Integer dimensione;

    @Column(name = "descrizione", nullable = true)
    private String descrizione;

    @Column(name = "stato", nullable = false, length = 16)
    private String stato;

    @Column(name = "data_cambio_stato")
    private LocalDate dataCambioStato;

    @Column(name = "data_caricamento")
    private LocalDate dataCaricamento;

    public LocalDate getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(LocalDate dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public LocalDate getDataCambioStato() {
        return dataCambioStato;
    }

    public void setDataCambioStato(LocalDate dataCambioStato) {
        this.dataCambioStato = dataCambioStato;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public String getEstensione() {
        return estensione;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estensione='" + estensione + '\'' +
                ", dimensione=" + dimensione +
                ", descrizione='" + descrizione + '\'' +
                ", stato='" + stato + '\'' +
                ", dataCambioStato=" + dataCambioStato +
                ", dataCaricamento=" + dataCaricamento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(id, documento.id) && Objects.equals(nome, documento.nome) && Objects.equals(estensione, documento.estensione) && Objects.equals(dimensione, documento.dimensione) && Objects.equals(descrizione, documento.descrizione) && Objects.equals(stato, documento.stato) && Objects.equals(dataCambioStato, documento.dataCambioStato) && Objects.equals(dataCaricamento, documento.dataCaricamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, estensione, dimensione, descrizione, stato, dataCambioStato, dataCaricamento);
    }
}