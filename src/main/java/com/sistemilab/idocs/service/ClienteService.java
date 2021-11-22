package com.sistemilab.idocs.service;

import com.sistemilab.idocs.model.Cliente;
import com.sistemilab.idocs.model.Utente;
import com.sistemilab.idocs.repository.ClienteRepository;
import com.sistemilab.idocs.repository.UtenteRepository;
import com.sistemilab.idocs.resource.CreateClienteRequest;
import io.quarkus.panache.common.Parameters;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Path("/customers")
public class ClienteService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class);

    @Inject
    ClienteRepository clienteRepository;
    @Inject
    UtenteRepository utenteRepository;

    public ClienteService(ClienteRepository clienteRepository, UtenteRepository utenteRepository) {
        this.clienteRepository  = clienteRepository;
        this.utenteRepository   = utenteRepository;
    }

    /**
     * Servizio che ritorna la lista dei clienti dell'utente passato in input
     *
     * @param userId
     * @return Lista di clienti
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/{userId}")
    public List<Cliente> list(@PathParam(value = "userId") String userId) throws Failure, WebApplicationException {
        Utente utente = utenteRepository.findById(Long.parseLong(userId));
        LOG.info("clienti dell'utente " + userId + ": " + utente.getClienti().stream().collect(Collectors.counting()));

        return utente.getClienti().stream().collect(Collectors.toList());
    }

    /**
     * Ritorna la lista di clienti NON associati al cliente passato in input
     * @TODO: possibile spostamento su UTENTE SERVICE ?
     *
     * @param userId
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/not/{userId}")
    public List<Cliente> listCustomers(@PathParam(value = "userId") String userId) throws Failure, WebApplicationException {
        List<Cliente> clientiOriginali = clienteRepository.findAll().stream().collect(Collectors.toList());
        List<Cliente> clientiFinali = new ArrayList<Cliente>();
        Utente utente = utenteRepository.findById(Long.parseLong(userId));
        if( utente != null) {
            List<Cliente> clientiDaEscludere = utente.getClienti().stream().collect(Collectors.toList());
            Boolean clienteIsPresente = false;
            for (Cliente cliente : clientiOriginali) {

                clienteIsPresente = false;
                for (Cliente clientePresente : clientiDaEscludere) {
                    if (cliente.getId() == clientePresente.getId()) {
                        clienteIsPresente = true;
                    }

                }
                if (!clienteIsPresente) {
                    clientiFinali.add(cliente);
                }

            }
        }

        LOG.info("GET CLIENTI NON ANCORA ASSOCIATI AD UTENTE "+ userId + " ha restituito "+ clientiFinali.size() + " clienti");
        return clientiFinali;
    }

    /**
     * Ritorna il cliente passato in input:
     * @// TODO: Spostando in UTENTESERVICE i servizi di GET, questo diventa semplice GET
     * @param customerId
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @GET
    @Path("/single/{customerId}")
    public Cliente getSingoloCliente(@PathParam(value = "customerId") String customerId) throws Failure, WebApplicationException {
        Cliente cliente = clienteRepository.findById(Long.parseLong(customerId));
        LOG.info("GET SINGOLO CLIENTE "+ customerId);

        return cliente;
    }

    /**
     * Servizio per la cancellazione del cliente
     *
     * @param idCliente
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @DELETE
    @Transactional
    @Path("/{idCliente}")
    public Response deleteCliente(@PathParam(value = "idCliente") String idCliente)throws Failure, WebApplicationException {
        LOG.info("CLIENTE DELETE START");
        try {
            Cliente cliente = clienteRepository.findById(Long.parseLong(idCliente));
            if (cliente != null)
                clienteRepository.delete(cliente);
            else
                return new ServerResponse("Cliente non presente in base dati", 500, new Headers<Object>());

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }

    /**
     * Servizio per la creazione del cliente
     *
     * @param createClienteRequest
     * @return Il cliente appena creato
     * @throws Failure
     * @throws WebApplicationException
     */
    @POST
    @Transactional
    public Response createCliente(@Valid CreateClienteRequest createClienteRequest) throws Failure, WebApplicationException {
        LOG.info("USER CREATION START");
        if(createClienteRequest.getIdUtente() == null){
            return new ServerResponse("ID UTENTE MANCANTE", 400, new Headers<Object>());
        }
        Cliente cliente = new Cliente();
                cliente.setRagioneSociale(createClienteRequest.getRagioneSociale());
                cliente.setPartitaIva(createClienteRequest.getPartitaIva());
                cliente.setNazione(createClienteRequest.getNazione());
                cliente.setDescrizione(createClienteRequest.getDescrizione());
        try {
            clienteRepository.create(cliente);
            Utente utente = utenteRepository.findById(createClienteRequest.getIdUtente());
            Set<Cliente> clienti = utente.getClienti();
            clienti.add(cliente);
            utente.setClienti(clienti);
            utenteRepository.persistAndFlush(utente);

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }

        Response.ResponseBuilder rb = Response.ok(cliente);
        return rb.build();
    }

}