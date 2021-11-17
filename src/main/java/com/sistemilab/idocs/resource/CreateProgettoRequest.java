package com.sistemilab.idocs.resource;

import javax.validation.constraints.NotBlank;

public class CreateProgettoRequest {

    private Long idCliente;

    @NotBlank(message = "nome may not be blank")
    private String nome;

    @NotBlank(message = "descrizione may not be blank")
    private String descrizione;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
