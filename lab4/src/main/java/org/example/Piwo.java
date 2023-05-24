package org.example;

import jakarta.persistence.*;


@NamedQueries({
        @NamedQuery(name="Piwo.tanszeNiz",
                query="SELECT p FROM Piwo p WHERE p.cena < :cena"),
        @NamedQuery(name="Piwo.piwaZBrowaruZCenaWiekszaNiz",
                query="SELECT p FROM Piwo p JOIN p.browar b WHERE b.name = :name AND p.cena > :cena")
})
@Entity
public class Piwo {
    @Id
    private String name;

    private long cena;
    @ManyToOne
    private Browar browar;

    public Piwo(String name, long cena, Browar browar) {
        this.name = name;
        this.cena = cena;
        this.browar = browar;
    }

    public Piwo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCena() {
        return cena;
    }

    public void setCena(long cena) {
        this.cena = cena;
    }

    public Browar getBrowar() {
        return browar;
    }

    public void setBrowar(Browar browar) {
        this.browar = browar;
    }

    @Override
    public String toString() {
        return "Piwo{" +
                "name='" + name + '\'' +
                ", cena=" + cena +
                '}';
    }
}

