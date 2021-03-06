package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString(callSuper = true)
@Entity // Une entité JPA
@Inheritance(strategy = InheritanceType.JOINED)

public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private String nom;
    
    @Column(unique=true)
    @NonNull
    private String adresse;
    
    
    @OneToMany(mappedBy = "client")
    private List<Transaction> achats = new LinkedList<>();
    
    public float budgetArt(int annee){
        float budget = 0;
        for (Transaction t : achats){
            if(t.getVenduLe().getYear()==annee){
                budget += t.getPrixVente();
            }
        }
        return budget;
    }
    
    public void ajouterTransaction(Transaction t){
        this.achats.add(t);
    }
 
}
