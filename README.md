# Backend del progetto iDocs

Il progetto è stato scritto usando [Quarkus, the Supersonic Subatomic Java Framework](https://quarkus.io/) con GraalVM e Java 17 ed utilizza Maven per la gestione delle dipendenze e la build. 

L'applicazione espone dei servizi REST esposti all'endpoint localhost:8080 testabili utilizzando la collection di Postman che si trova dentro resource. Una prima versione della documentazione secondo le specifiche OpenAPI/Swagger è raggiungibile a questo [link](http://localhost:8080/q/swagger-ui/) (quando l'applicazione è up & running).

L'intero progetto utilizza una base dati PostgreSQL containerizzata che viene avviata/stoppata mediante script contenuti nella cartella /bin. Ad ogni avvio vengono precaricati alcuni dati che si possono trovare nel file /bin/data.sql.

## Per avviare il container del db:

```shell script
./bin./start_postgres.sh
```
## Per stoppare il container del db:

```shell script
./bin/stop_postgres.sh
```

## Per eseguire l'applicazione in dev mode:

```shell script
mvn compile quarkus:dev
```

## Packaging ed esecuzione dell'applicazione:

```shell script
mvn package
```

### TODO LIST
- [ ] Utilizzare token JWT in tutte le chiamate
- [ ] Salvataggio criptato della password dell'utente
- [ ] Migliorare commenti
- [ ] Migliorare test automaticci

### NICE TO HAVE
- [ ] NoSql DB
- [ ] Pipeline di CI
- [ ] Compose per lancio dell'intera applicazione (compreso il FE) con unico comando
