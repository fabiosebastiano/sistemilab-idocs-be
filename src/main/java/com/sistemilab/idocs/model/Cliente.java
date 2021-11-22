package com.sistemilab.idocs.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "cliente", indexes = {
        @Index(name = "cliente_partita_iva_key", columnList = "partita_iva", unique = true),
        @Index(name = "cliente_ragione_sociale_key", columnList = "ragione_sociale", unique = true)
})
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "CLIENTE_PROGETTO",
            joinColumns = { @JoinColumn(name = "ID_CLIENTE") },
            inverseJoinColumns = { @JoinColumn(name = "ID_PROGETTO") })
    private Set<Progetto> progetti = new HashSet<>();

    @Column(name = "ragione_sociale", nullable = false, length = 64)
    private String ragioneSociale;

    @Column(name = "partita_iva", nullable = false, length = 64)
    private String partitaIva;

    @Column(name = "nazione", nullable = false)
    private String nazione;

    @Column(name = "descrizione")
    private String descrizione;

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Progetto> getProgetti() {
        return progetti;
    }

    public void setProgetti(Set<Progetto> progetti) {
        this.progetti = progetti;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", progetti=" + progetti +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", nazione='" + nazione + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(progetti, cliente.progetti) && Objects.equals(ragioneSociale, cliente.ragioneSociale) && Objects.equals(partitaIva, cliente.partitaIva) && Objects.equals(nazione, cliente.nazione) && Objects.equals(descrizione, cliente.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, progetti, ragioneSociale, partitaIva, nazione, descrizione);
    }
}