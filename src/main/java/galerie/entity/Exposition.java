package galerie.entity;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column
    @NonNull
    private LocalDate debut;
    
    @Column(unique=true)
    @NonNull
    private String intitule;
    
    @Column
    private int duree;
    
    @ManyToOne
    @NonNull
    private Galerie organisateur;
    
    @OneToMany(mappedBy = "lieuDeVente")
    private List<Transaction> ventes = new LinkedList<>();
    
    @ManyToMany
    @JoinTable(name="expo_tableau",joinColumns = @JoinColumn(name = "exposition_id", referencedColumnName="id"),inverseJoinColumns = @JoinColumn(name = "tableau_id",  referencedColumnName="id")) 
    private List<Tableau> oeuvres = new LinkedList<>();

    
    
    public float CA(){
        float chiffreAff = 0;
        for(Transaction t : ventes){
            chiffreAff += t.getPrixVente();
        }
        return chiffreAff;
    }
    
    public Exposition(int id, String intitule, int duree, Galerie organisateur){
        this.id = id;
        this.debut = LocalDate.now();
        this.intitule = intitule;
        this.duree = duree;
        this.organisateur = organisateur;
    }
    
    public void ajouterTransaction(Transaction t){
        this.ventes.add(t);
    }
}
