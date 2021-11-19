package com.sistemilab.idocs.service;

import com.sistemilab.idocs.model.Cliente;
import com.sistemilab.idocs.model.Utente;
import com.sistemilab.idocs.repository.ClienteRepository;
import com.sistemilab.idocs.repository.UtenteRepository;
import com.sistemilab.idocs.resource.AuthenticateUserRequest;
import com.sistemilab.idocs.resource.CreateUserRequest;
import com.sistemilab.idocs.resource.UpdateUserRequest;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtenteService {

    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied", 401, new Headers<>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());


    private static final Logger LOG = Logger.getLogger(UtenteService.class);

    @Inject
    UtenteRepository repository;

    @Inject
    ClienteRepository clienteRepository;

    public UtenteService(UtenteRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public List<Utente> getAllUsers(@PathParam(value = "userId") String userId) throws Failure, WebApplicationException {
        LOG.info("GET ALL USERS START");

        List<Utente> usersList = repository.findOtherUsers(Long.parseLong(userId));

        return usersList;

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(AuthenticateUserRequest authRequest) throws Failure, WebApplicationException {
        LOG.info("LOGIN START");
        if(authRequest.getUsername().isEmpty() || authRequest.getPassword().isEmpty())
        {
            return ACCESS_DENIED;
        }

        Optional<Utente> user = repository.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        if ( user.isEmpty() ){
            return ACCESS_DENIED;
        }else {
           // Response.ResponseBuilder rb = Response.ok(user.map(usr -> usr.getUsername()));
            LOG.info(user.get().getClienti());
            Response.ResponseBuilder rb = Response.ok(user);

            return rb.build();
        }
        //ritorna token

    }

    @POST
    @Transactional
    public Response createUser(@Valid CreateUserRequest createRequest) throws Failure, WebApplicationException {
        LOG.info("USER CREATION START");
         Utente utente = new Utente();
        utente.setNome(createRequest.getNome());
        utente.setCognome(createRequest.getCognome());
        utente.setUsername(createRequest.getUsername());
        utente.setPassword(createRequest.getPassword());
        utente.setMail(createRequest.getMail());

        try {
            repository.create(utente);

        } catch (Exception e) {
            return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
        }

        Response.ResponseBuilder rb = Response.ok(utente);
        return rb.build();
    }

    @POST
    @Transactional
    @Path("/addCliente")
    public Response updateClientiAssociati(@Valid UpdateUserRequest updateRequest) throws Failure, WebApplicationException {
        LOG.info("USER CUSTOMERS UPDATE START FOR USER " + updateRequest.getIdUtente() + " AND CUSTOMER "+ updateRequest.getIdClienteDaAggiungere());
       Utente utente = repository.findById(updateRequest.getIdUtente());
        if (utente != null) {
            Set<Cliente> listaClienti = utente.getClienti();
            Cliente nuovoCliente = clienteRepository.findById(updateRequest.getIdClienteDaAggiungere());
            if (nuovoCliente != null) {
                listaClienti.add(nuovoCliente);
                utente.setClienti(listaClienti);
                try {
                    repository.persistAndFlush(utente);

                } catch (Exception e) {
                    return new ServerResponse(e.getMessage(), 500, new Headers<Object>());
                }

            }

        }

        Response.ResponseBuilder rb = Response.ok();
        return rb.build();

    }

}