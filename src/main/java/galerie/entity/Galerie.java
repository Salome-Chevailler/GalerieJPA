package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

public class Galerie {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @Column(unique=true)
    @NonNull
    private String adresse;

    
    @OneToMany(mappedBy = "organisateur")
    public List<Exposition> evenements = new LinkedList<>();
    
    private float CAannuel(int annee){
        float chiffreAffAnnuel = 0;
        for(Exposition e : evenements){
            if(e.getDebut().getYear()==annee && (e.getDebut().getDayOfYear()+e.getDuree())<365){
                chiffreAffAnnuel += e.CA();
            }
        }
        return chiffreAffAnnuel;
    }
    
    public Galerie(int id, String nom, String ad){
        this.id = id;
        this.nom = nom;
        this.adresse = ad;
    }
    
    public void ajouterExposition(Exposition e){
        this.evenements.add(e);
    }
    
    
}
