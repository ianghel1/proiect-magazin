package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Produs")
@Table(name = "produs")
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nume")
    private String nume;
    @Column(name = "pret")
    private Double pret;
    @Column(name = "cantitate_stoc")
    private int cantitateStoc;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "producator_produs", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "producatorId"))
    private List<Producator>  producator = new ArrayList<Producator>();

    public Produs() {
//        contructor empty by default
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public int getCantitateStoc() {
        return cantitateStoc;
    }

    public void setCantitateStoc(int cantitateStoc) {
        this.cantitateStoc = cantitateStoc;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitateStoc=" + cantitateStoc +
                '}';
    }
}
