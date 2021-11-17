package com.sistemilab.idocs.repository;

import com.sistemilab.idocs.model.Cliente;
import com.sistemilab.idocs.model.Progetto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProgettoRepository implements PanacheRepository<Progetto> {

    public List<Progetto> findByCustomerId(Integer customerId){
        return find("id = :customerId",
                Parameters.with("customerId", customerId)).stream().collect(Collectors.toList());
    }

    public void create(Progetto progetto){
        persistAndFlush(progetto);
    }
}
