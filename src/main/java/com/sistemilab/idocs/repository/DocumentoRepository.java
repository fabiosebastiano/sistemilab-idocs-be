package com.sistemilab.idocs.repository;

import com.sistemilab.idocs.model.Documento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DocumentoRepository implements PanacheRepository<Documento> {

    public List<Documento> findByProjectId(Integer projectId){
        return find("id = :projectId and eliminato is not true",
                Parameters.with("projectId", projectId)).stream().collect(Collectors.toList());
    }

    public void create(Documento documento){
        persistAndFlush(documento);
    }

}
