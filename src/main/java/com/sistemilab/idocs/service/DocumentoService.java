package com.sistemilab.idocs.service;

import com.sistemilab.idocs.model.Documento;
import com.sistemilab.idocs.model.Progetto;
import com.sistemilab.idocs.repository.DocumentoRepository;
import com.sistemilab.idocs.repository.ProgettoRepository;
import com.sistemilab.idocs.resource.CreateDocumentoRequest;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;

import javax.inject.Inject;
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

    /**
     * Servizio che restituisce tutti i documenti associati al progetto passato in input
     * @param projectId
     * @return Lista di documenti
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/{projectId}")
    public List<Documento> list(@PathParam(value = "projectId") String projectId) throws Failure, WebApplicationException {
        Progetto progetto = progettoRepository.findById(Long.parseLong(projectId));
        LOG.info("Documenti del progetto " + projectId);
        return progetto.getDocumenti().stream().collect(Collectors.toList());
    }

    /**
     * Servizio per la creazione di un nuovo documento
     *
     * @param createDocumentoRequest
     * @return Il documento appena creato
     * @throws Failure
     * @throws WebApplicationException
     */
    @POST
    @Transactional
    public Response createDocumento(@Valid CreateDocumentoRequest createDocumentoRequest) throws Failure, WebApplicationException {
        LOG.info("DOC CREATION START");
        Documento documento = new Documento();
        documento.setNome(createDocumentoRequest.getNome());
        documento.setEstensione(createDocumentoRequest.getEstensione());
        documento.setDimensione(createDocumentoRequest.getDimensione());
        documento.setStato("DA APPROVARE");
        documento.setDescrizione(createDocumentoRequest.getDescrizione());
        documento.setDataCaricamento(LocalDate.now());
        try {
            documentoRepository.create(documento);
            Progetto progetto = progettoRepository.findById(createDocumentoRequest.getIdProgetto());
            Set<Documento> documenti = progetto.getDocumenti();
            documenti.add(documento);
            progetto.setDocumenti(documenti);
            progettoRepository.persistAndFlush(progetto);
        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok(documento);
        return rb.build();
    }

    /**
     * Servizio tramite il quale approvare il documento passato in input
     * @// TODO: centralizzare in unico servizio che gestisce approvazione/rifiuto
     *
     * @param documentId
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @PUT
    @Transactional
    @Path("/approva/{documentId}")
    public Response approvaDocumento(@PathParam(value = "documentId") String documentId) throws Failure, WebApplicationException {
        LOG.info("DOC APPROVAL START "+ documentId);
        Documento documento = documentoRepository.findById(Long.parseLong(documentId));
        documento.setStato("APPROVATO");
        documento.setDataCambioStato(LocalDate.now());
        try {
            documentoRepository.persistAndFlush(documento);
        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok(documento);
        return rb.build();
    }

    /**
     * Servizio tramite il quale rifiutare il documento passato in input
     * @// TODO: centralizzare in unico servizio che gestisce approvazione/rifiuto
     *
     * @param documentId
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @PUT
    @Transactional
    @Path("/rifiuta/{documentId}")
    public Response rifiutaDocumento(@PathParam(value = "documentId") String documentId) throws Failure, WebApplicationException {
        LOG.info("DOC REFUSE START "+ documentId);
        Documento documento = documentoRepository.findById(Long.parseLong(documentId));
        documento.setStato("RIFIUTATO");
        documento.setDataCambioStato(LocalDate.now());

        try {
            documentoRepository.persistAndFlush(documento);
        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok(documento);
        return rb.build();
    }

    /**
     * Servizio per la cancellazione di un documento
     *
     * @param idDocumento
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @DELETE
    @Transactional
    @Path("/{idDocumento}")
    public Response deleteDocumento(@PathParam(value = "idDocumento") String idDocumento)throws Failure, WebApplicationException {
        LOG.info("DOCUMENTO DELETE START");
        try {
            Documento documento = documentoRepository.findById(Long.parseLong(idDocumento));
            if (documento != null)
                documentoRepository.delete(documento);
            else
                return new ServerResponse("Documento non presente in base dati", 500, new Headers<Object>());

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }

}