package com.sistemilab.idocs.resource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

    public Long idUtente;

    public Long idClienteDaAggiungere;


    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long id) {
        this.idUtente = id;
    }

    public Long getIdClienteDaAggiungere() {
        return idClienteDaAggiungere;
    }

    public void setIdClienteDaAggiungere(Long idClienteDaAggiungere) {
        this.idClienteDaAggiungere = idClienteDaAggiungere;
    }
}
