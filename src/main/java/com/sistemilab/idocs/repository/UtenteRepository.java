package com.sistemilab.idocs.repository;

import com.sistemilab.idocs.model.Utente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UtenteRepository implements PanacheRepository<Utente> {

    public Optional<Utente> findByUsernameAndPassword(String username, String password){
        return find("username = :username and password = :password",
                Parameters.with("username", username)
                        .and("password", password))
                .stream()
                .findFirst();
    }

    public List<Utente> findOtherUsers(Long userId){
        return find("id <> :userId order by ID",
                Parameters.with("userId", userId))
                .stream().collect(Collectors.toList());
    }

    public void create(Utente utente){
       persistAndFlush(utente);
    }
}
