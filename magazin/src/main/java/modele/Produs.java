package modele;

import javax.persistence.*;

@Entity(name = "Produs")
@Table(name = "produs")
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nume")
    private String nume;
    @Column(name = "pret")
    private Double pret;
    @Column(name = "cantitate_stoc")
    private int cantitateStoc;

    @OneToMany(mappedBy = "producator")
    private Producator producator;

    public Produs() {
//        contructor empty by default
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
