package com.sistemilab.idocs.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateDocumentoRequest {

    private Long idProgetto;

    private Long idDocumento;

    @NotBlank(message = "nome may not be blank")
    private String nome;

    @NotBlank(message = "formato may not be blank")
    private String formato;

    private Boolean approvato = false;

    private LocalDate dataApprovazione;


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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Boolean getApprovato() {
        return approvato;
    }

    public void setApprovato(Boolean approvato) {
        this.approvato = approvato;
    }

    public LocalDate getDataApprovazione() {
        return dataApprovazione;
    }

    public void setDataApprovazione(LocalDate dataApprovazione) {
        this.dataApprovazione = dataApprovazione;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }
}
