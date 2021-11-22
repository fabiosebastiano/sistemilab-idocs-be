package com.sistemilab.idocs.service;

import com.sistemilab.idocs.model.Cliente;
import com.sistemilab.idocs.model.Progetto;
import com.sistemilab.idocs.repository.ClienteRepository;
import com.sistemilab.idocs.repository.ProgettoRepository;
import com.sistemilab.idocs.resource.CreateProgettoRequest;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/projects")
public class ProgettoService {

    private static final Logger LOG = Logger.getLogger(ProgettoService.class);

    @Inject
    ProgettoRepository progettoRepository;

    @Inject
    ClienteRepository clienteRepository;

    public ProgettoService(ClienteRepository clienteRepository, ProgettoRepository progettoRepository) {
        this.clienteRepository  = clienteRepository;
        this.progettoRepository  = progettoRepository;
    }


    /**
     * Servizio che ritorna la lista di progetti associati ad un cliente
     *
     * @param customerId
     * @return Lista di progetti
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/{customerId}")
    public List<Progetto> list(@PathParam(value = "customerId") String customerId) throws Failure, WebApplicationException {
        Cliente cliente = clienteRepository.findById(Long.parseLong(customerId));
        LOG.info("Progetti del cliente " + customerId + " in tutto " + cliente.getProgetti().stream().collect(Collectors.counting()));
        return cliente.getProgetti().stream().collect(Collectors.toList());
    }

    /**
     * Servizio che ritorna il singolo progetto
     * @param projectId
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/single/{projectId}")
    public Progetto getSingoloProgetto(@PathParam(value = "projectId") String projectId) throws Failure, WebApplicationException {
        Progetto progetto = progettoRepository.findById(Long.parseLong(projectId));
        LOG.info("GET SINGOLO CLIENTE");

        return progetto;
    }

    /**
     * Servizio per la creazione di un nuovo progetto
     *
     * @param createProgettoRequest
     * @return Il progetto appena creato
     * @throws Failure
     * @throws WebApplicationException
     */
    @POST
    @Transactional
    public Response createProgetto(@Valid CreateProgettoRequest createProgettoRequest) throws Failure, WebApplicationException {
        LOG.info("PROGETTO CREATION START for customer "+ createProgettoRequest.getIdCliente());
        if(createProgettoRequest.getIdCliente() == null){
            return new ServerResponse("ID CLIENTE MANCANTE", 400, new Headers<Object>());
        }
        Progetto progetto = new Progetto();
        progetto.setNome(createProgettoRequest.getNome());
        progetto.setDescrizione(createProgettoRequest.getDescrizione());

        try {
            progettoRepository.create(progetto);

            Cliente cliente = clienteRepository.findById(createProgettoRequest.getIdCliente());
            Set<Progetto> progetti = cliente.getProgetti();
            progetti.add(progetto);
            cliente.setProgetti(progetti);
            clienteRepository.persistAndFlush(cliente);

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }

        Response.ResponseBuilder rb = Response.ok(progetto);
        return rb.build();
    }

    /**
     * Servizio per la cancellazione del progetto
     *
     * @param idProgetto
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @DELETE
    @Transactional
    @Path("/{idProgetto}")
    public Response deleteProgetto(@PathParam(value = "idProgetto") String idProgetto)throws Failure, WebApplicationException {
        LOG.info("PROGETTO DELETE START");
        try {
            Progetto progetto = progettoRepository.findById(Long.parseLong(idProgetto));
            if (progetto != null)
                progettoRepository.delete(progetto);
            else
                return new ServerResponse("Progetto non presente in base dati", 500, new Headers<Object>());

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }


}