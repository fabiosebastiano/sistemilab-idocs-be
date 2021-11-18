package com.sistemilab.idocs.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateDocumentoRequest {

    private Long idProgetto;

    private Long idDocumento;

    @NotBlank(message = "nome may not be blank")
    private String nome;

    @NotBlank(message = "estensione may not be blank")
    private String estensione;

    private Integer dimensione;

    private String descrizione;

    private String stato = "DA APPROVARE";

    private LocalDate dataCambioStato;

    private LocalDate dataCaricamento;

    public LocalDate getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(LocalDate dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public Long getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(Long idProgetto) {
        this.idProgetto = idProgetto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstensione() {
        return estensione;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
    }


    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataCambioStato() {
        return dataCambioStato;
    }

    public void setDataCambioStato(LocalDate dataCambioStato) {
        this.dataCambioStato = dataCambioStato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
