# Backend del progetto iDocs

Il progetto è stato scritto usando [Quarkus, the Supersonic Subatomic Java Framework](https://quarkus.io/) con GraalVM e Java 17 ed utilizza Maven per la gestione delle dipendenze e la build. 




L'applicazione espone dei servizi REST esposti all'endpoint localhost:8080 testabili utilizzando la collection di Postman inclusa.

L'intero progetto utilizza una base dati PostgreSQL containerizzata che viene avviata/stoppata mediante script contenuti nella cartella /bin.

## Per eseguire l'applicazione in dev mode:

```shell script
./mvnw compile quarkus:dev
```

## Packaging ed esecuzione dell'applicazione:

```shell script
./mvnw package
```

### TODO LIST
- [ ] Migliorare commenti
- [ ] Migliorare test automaticci

### NICE TO HAVE
- [ ] Pipeline di CI
- [ ] Compose per lancio dell'intera applicazione (compreso il FE) con unico comando
