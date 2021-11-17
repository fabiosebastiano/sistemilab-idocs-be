package com.sistemilab.idocs.repository;

import com.sistemilab.idocs.model.Documento;
import com.sistemilab.idocs.model.Progetto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DocumentoRepository implements PanacheRepository<Documento> {

    public List<Documento> findByProjectId(Integer projectId){
        return find("id = :projectId",
                Parameters.with("projectId", projectId)).stream().collect(Collectors.toList());
    }

    public void create(Documento documento){
        persistAndFlush(documento);
    }
    
}
