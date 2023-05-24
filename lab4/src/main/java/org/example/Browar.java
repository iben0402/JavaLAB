package org.example;

import jakarta.persistence.*;

import java.util.List;

@NamedQueries({
        @NamedQuery(name="Browar.deleteName",
                query="DELETE FROM Browar b WHERE b.name = :name"),
        @NamedQuery(name="Browar.piwaTanszeNiz",
                query="SELECT b FROM Browar b JOIN b.piwa p WHERE p.cena < :cena")
})

@Entity
public class Browar {
    @Id
    private String name;

    private long wartosc;

    @OneToMany(mappedBy = "browar", cascade = CascadeType.ALL)
    private List<Piwo> piwa;

    public Browar(String name, long wartosc, List<Piwo> piwa) {
        this.name = name;
        this.wartosc = wartosc;
        this.piwa = piwa;
    }

    public Browar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWartosc() {
        return wartosc;
    }

    public void setWartosc(long wartosc) {
        this.wartosc = wartosc;
    }

    public List<Piwo> getPiwa() {
        return piwa;
    }

    public void setPiwa(List<Piwo> piwa) {
        this.piwa = piwa;
    }

    @Override
    public String toString() {
        return "Browar{" +
                "name='" + name + '\'' +
                ", wartosc=" + wartosc;
    }
}
