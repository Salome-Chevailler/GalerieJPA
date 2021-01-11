package galerie.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

public class Transaction { 
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private LocalDate venduLe;
    
    @Column(unique=true)
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
}