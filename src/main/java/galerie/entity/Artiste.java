package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString(callSuper = true)
@Entity // Une entité JPA

public class Artiste extends Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private String biographie;
    
    public Artiste(String nom, String adresse, String biographie) {
        super(nom, adresse);
        this.biographie = biographie;
    }
    
    @OneToMany(mappedBy = "auteur")
    @NonNull
    private List<Tableau> oeuvres = new LinkedList<>();
    
    
    
}
