package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private String titre;
    
    @Column
    @NonNull
    private String support;

    @Column
    private int largeur;
    
    @Column
    private int hauteur;
    
    @ManyToMany(mappedBy = "oeuvres")
    private List<Exposition> accrochages = new LinkedList<>();
    
    @OneToOne(mappedBy = "oeuvre")
    private Transaction vendu;
    
    @ManyToOne
    private Artiste auteur;
}

