package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Producator")
@Table(name = "producator")
public class Producator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producatorId")
    private int producatorId;

    @Column(name = "nume")
    private String nume;

    @ManyToMany(mappedBy = "producator", fetch = FetchType.EAGER)
//    @Column(name = "produse")
    private List<Produs> produse = new ArrayList<Produs>();

    public Producator() {
//        constructor empty by default
    }

    public int getProducatorId() {
        return producatorId;
    }

    public void setProducatorId(int producatorId) {
        this.producatorId = producatorId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List getProduse() {
        return produse;
    }

    public void setProduse(List produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Producator{" +
                "id=" + producatorId +
                ", nume='" + nume + '\'' +
                ", produse='" + produse + '\'' +
                '}';
    }
}
