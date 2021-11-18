package com.sistemilab.idocs.repository;

import com.sistemilab.idocs.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> findByCustomerId(Integer customerId){
        return find("id = :customerId",
                Parameters.with("customerId", customerId)).stream().collect(Collectors.toList());
    }


    public void create(Cliente cliente){
        persistAndFlush(cliente);
    }
}
