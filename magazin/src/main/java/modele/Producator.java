package modele;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Producator")
@Table(name = "producator")
public class Producator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nume")
    private String nume;

    @ManyToOne
    @Column(name = "produse")
    private List<Produs> produse;

    public Producator() {
//        constructor empty by default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", produse='" + produse + '\'' +
                '}';
    }
}
