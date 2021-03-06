package galerie.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

public class Transaction { 
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column
    @NonNull
    private LocalDate venduLe;
    
    @Column
    private float prixVente;
    
    @ManyToOne
    @NonNull
    private Exposition lieuDeVente;
    
    @OneToOne
    @NonNull
    private Tableau oeuvre;
    
    @ManyToOne
    @NonNull
    private Personne client;
    
    public Transaction(int id, LocalDate venduLe, float prixVente, Exposition expo){
        this.id = id;
        this.venduLe = venduLe;
        this.prixVente = prixVente;
        this.lieuDeVente = expo;
    }
    
    public Transaction(int id, LocalDate venduLe, float prixVente, Personne p){
        this.id = id;
        this.venduLe = venduLe;
        this.prixVente = prixVente;
        this.client = p;
    }
}
