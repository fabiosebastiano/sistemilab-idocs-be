package com.sistemilab.idocs.service;

import com.sistemilab.idocs.model.Cliente;
import com.sistemilab.idocs.model.Documento;
import com.sistemilab.idocs.model.Progetto;
import com.sistemilab.idocs.repository.ClienteRepository;
import com.sistemilab.idocs.repository.DocumentoRepository;
import com.sistemilab.idocs.repository.ProgettoRepository;
import com.sistemilab.idocs.resource.CreateDocumentoRequest;
import com.sistemilab.idocs.resource.CreateProgettoRequest;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;

import javax.inject.Inject;
import javax.print.Doc;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/docs")
public class DocumentoService {

    private static final Logger LOG = Logger.getLogger(DocumentoService.class);

    @Inject
    ProgettoRepository progettoRepository;

    @Inject
    DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository, ProgettoRepository progettoRepository) {
        this.documentoRepository  = documentoRepository;
        this.progettoRepository  = progettoRepository;
    }


    @GET
    @Path("/{projectId}")
    public List<Documento> list(@PathParam(value = "projectId") String projectId) throws Failure, WebApplicationException {
        Progetto progetto = progettoRepository.findById(Long.parseLong(projectId));
        LOG.info("Documenti del progetto " + projectId);
        LOG.info(progetto.getDocumenti().stream().collect(Collectors.toList()));
        return progetto.getDocumenti().stream().collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response createDocumento(@Valid CreateDocumentoRequest createDocumentoRequest) throws Failure, WebApplicationException {
        LOG.info("DOC CREATION START");
        LOG.info(createDocumentoRequest.getNome());
        Documento documento = new Documento();
        documento.setNome(createDocumentoRequest.getNome());
        documento.setFormato(createDocumentoRequest.getFormato());
        documento.setApprovato(false);

        try {
            documentoRepository.create(documento);
            LOG.info("DOC CREATO CON ID: " + documento.getId());

            Progetto progetto = progettoRepository.findById(createDocumentoRequest.getIdProgetto());

            Set<Documento> documenti = progetto.getDocumenti();

            LOG.info("DOCUMENTI DEL PROGETTO " + createDocumentoRequest.getIdProgetto() + " PRIMA DELL'AGGIORNAMENTO: " + documenti.stream().count());

            documenti.add(documento);
            progetto.setDocumenti(documenti);
            progettoRepository.persistAndFlush(progetto);

            LOG.info("DOCUMENTI DEL PROGETTO " + createDocumentoRequest.getIdProgetto() + " DOPO L'AGGIORNAMENTO: " + progetto.getDocumenti().stream().count());


        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }

        Response.ResponseBuilder rb = Response.ok(documento);
        return rb.build();

    }

    @PUT
    @Transactional
    @Path("/{documentId}")
    public Response approvaDocumento(@PathParam(value = "documentId") String documentId) throws Failure, WebApplicationException {
        LOG.info("DOC APPROVAL START "+ documentId);
        Documento documento = documentoRepository.findById(Long.parseLong(documentId));
        documento.setApprovato(true);
        documento.setDataApprovazione(LocalDate.now());

        try {
            documentoRepository.persistAndFlush(documento);


        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }

        Response.ResponseBuilder rb = Response.ok(documento);
        return rb.build();

    }

}