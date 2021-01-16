package galerie.dao;

import galerie.entity.Galerie;
import galerie.entity.Exposition;
import galerie.entity.Personne;
import galerie.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;
    
    @Autowired
    private ExpositionRepository expositionDAO;
    
    @Autowired
    private PersonneRepository personneDAO;

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        //Pour la table Galerie
        log.info("On compte les enregistrements de la table 'Galerie'");
        int combienDansLeJeuDeTest = 1; 
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 galerie" );
        
        //Pour la table Exposition
        log.info("On compte les enregistrements de la table 'Exposition'");
        int combienDansLeJeuDeTest2 = 2;
        long nombre2 = expositionDAO.count();
        assertEquals(combienDansLeJeuDeTest2, nombre2, "On doit trouver 1 exposition" );
        
        //Pour la table Personne
        log.info("On compte les enregistrements de la table 'Personne'");
        int combienDansLeJeuDeTest3 = 2;
        long nombre3 = personneDAO.count();
        assertEquals(combienDansLeJeuDeTest3, nombre3, "On doit trouver 1 personne" );
    }
    
    @Test
    public void listerLesEntites() {
        //Pour GalerieRepository
	log.info("Lister les entités");
	List<Galerie> liste = galerieDAO.findAll(); // Renvoie la liste des entités dans la table
        log.info("Liste des entités: {}", liste);
        
        //Pour ExpositionRepository
        log.info("Lister les entités");
	List<Exposition> liste2 = expositionDAO.findAll(); // Renvoie la liste des entités dans la table
        log.info("Liste des entités: {}", liste2);
       
        // Pour PersonneRepository
        log.info("Lister les entités");
	List<Personne> liste3 = personneDAO.findAll(); // Renvoie la liste des entités dans la table
        log.info("Liste des entités: {}", liste3);
    }
	
    @Test
    @Sql("test-data.sql")
    public void trouverParCle() {
        //Pour Galerie
	log.info("Trouver une entité par sa clé");
        int idPresent = 1;
	Optional<Galerie> resultat = galerieDAO.findById(idPresent);
	// On s'assure qu'on trouve le résultat
	assertTrue(resultat.isPresent(), "Cette galerie existe");
	Galerie g = resultat.get();
	assertEquals("Saatchi", g.getNom());
        log.info("Entité trouvée: {}", g);
        
        //Pour Exposition
        log.info("Trouver une entité par sa clé");
	Optional<Exposition> resultat2 = expositionDAO.findById(idPresent);
	// On s'assure qu'on trouve le résultat
	assertTrue(resultat2.isPresent(), "Cette exposition existe");
	Exposition e = resultat2.get();
	assertEquals("expo", e.getIntitule());
        log.info("Entité trouvée: {}", e);
    
        //Pour Personne
        log.info("Trouver une entité par sa clé");
	Optional<Personne> resultat3 = personneDAO.findById(idPresent);
	// On s'assure qu'on trouve le résultat
	assertTrue(resultat3.isPresent(), "Cette exposition existe");
	Personne p = resultat3.get();
	assertEquals("Dupont", p.getNom());
        log.info("Entité trouvée: {}", p);
	}
    
    @Test
    @Sql("test-data.sql")
    public void entiteInconnue()  {
        //Pour Galerie
	log.info("Chercher une entité inconnue");
	int idInconnu = 99;
        Optional<Galerie> resultat = galerieDAO.findById(idInconnu);
        assertFalse(resultat.isPresent(), "Cette galerie n'existe pas");
        
        //Pour Exposition
        log.info("Chercher une entité inconnue");
        Optional<Exposition> resultat2 = expositionDAO.findById(idInconnu);
        assertFalse(resultat2.isPresent(), "Cette exposition n'existe pas");
        
        //Pour Personne
        log.info("Chercher une entité inconnue");
        Optional<Personne> resultat3 = personneDAO.findById(idInconnu);
        assertFalse(resultat3.isPresent(), "Cette personne n'existe pas");
    }
    
    	
    @Test
    @Sql("test-data.sql")
    public void creerUneEntite()  {
        //Pour Galerie
	log.info("Créer une entité");
	Galerie nouvelle = new Galerie();
	nouvelle.setNom("essai");
	nouvelle.setAdresse("essai");
	assertNull(nouvelle.getId(), "L'entité n'a pas encore de clé");
	galerieDAO.save(nouvelle); // 'save' enregistre l'entite dans la base
	Integer nouvellecle = nouvelle.getId(); // La clé a été auto-générée lors de l'enregistrement
	assertNotNull(nouvellecle, "Une nouvelle clé doit avoir été générée");
	log.info("Nouvelle entité: {}", nouvelle);
        
        //Pour Exposition
        log.info("Créer une entité");
	Exposition nouvelle2 = new Exposition();
	nouvelle2.setDebut(LocalDate.of(2020, Month.MARCH, 15));
        nouvelle2.setIntitule("Exposition");
        nouvelle2.setDuree(1);
	assertNull(nouvelle2.getId(), "L'entité n'a pas encore de clé");
	expositionDAO.save(nouvelle2); // 'save' enregistre l'entite dans la base
	Integer nouvellecle2 = nouvelle2.getId(); // La clé a été auto-générée lors de l'enregistrement
	assertNotNull(nouvellecle2, "Une nouvelle clé doit avoir été générée");
	log.info("Nouvelle entité: {}", nouvelle2);
        
        //Pour Personne
        log.info("Créer une entité");
	Personne nouvelle3 = new Personne();
	nouvelle3.setNom("Nom");
        nouvelle3.setAdresse("Adresse");
	assertNull(nouvelle3.getId(), "L'entité n'a pas encore de clé");
	personneDAO.save(nouvelle3); // 'save' enregistre l'entite dans la base
	Integer nouvellecle3 = nouvelle3.getId(); // La clé a été auto-générée lors de l'enregistrement
	assertNotNull(nouvellecle3, "Une nouvelle clé doit avoir été générée");
	log.info("Nouvelle entité: {}", nouvelle3);
    }
    
    @Test
    @Sql("test-data.sql")
    public void erreurCreationEntite() {
        //Pour Galerie
	log.info("Créer une entité avec erreur");
	Galerie nouvelle = new Galerie();
	nouvelle.setNom("Saatchi");  // Ce nom existe dans le jeu de test
	nouvelle.setAdresse("essai");
	try { // L'enregistreement peut générer des exceptions (ex : violation de contrainte d'intégrité)
            galerieDAO.save(nouvelle);
            fail("Les noms doivent être tous distincts, on doit avoir une exception");
	} catch (DataIntegrityViolationException e) {
			// Si on arrive ici c'est normal, on a eu l'exception attendue
	}
	assertNull(nouvelle.getId(), "La clé n'a pas été générée, l'entité n'est pas enregistrée");
        
        //Pour Exposition
        log.info("Créer une entité avec erreur");
	Exposition nouvelle2 = new Exposition();
	nouvelle2.setDebut(LocalDate.of(2020, Month.MARCH, 15));
        nouvelle2.setIntitule("expo");
        nouvelle2.setDuree(1);
	try { // L'enregistreement peut générer des exceptions (ex : violation de contrainte d'intégrité)
            expositionDAO.save(nouvelle2);
            fail("Les intitulés doivent être tous distincts, on doit avoir une exception");
	} catch (DataIntegrityViolationException e) {
			// Si on arrive ici c'est normal, on a eu l'exception attendue
	}
	assertNull(nouvelle2.getId(), "La clé n'a pas été générée, l'entité n'est pas enregistrée");
        
        //Pour Personne
        log.info("Créer une entité avec erreur");
	Personne nouvelle3 = new Personne();
	nouvelle3.setNom("Dupont");
        nouvelle3.setAdresse("Adresse");
	try { // L'enregistreement peut générer des exceptions (ex : violation de contrainte d'intégrité)
            personneDAO.save(nouvelle3);
            fail("Les intitulés doivent être tous distincts, on doit avoir une exception");
	} catch (DataIntegrityViolationException e) {
			// Si on arrive ici c'est normal, on a eu l'exception attendue
	}
	assertNull(nouvelle3.getId(), "La clé n'a pas été générée, l'entité n'est pas enregistrée");
    }
    
    @Test
    //Test permettant de tester la méthode CAannuel() de Galerie
    public void calculerCAAnnuel(){
        Galerie g = new Galerie(1, "Galerie", "Adresse");
        Exposition e = new Exposition(1, "Intitule", 1, g);
        Transaction t = new Transaction(1, LocalDate.now(), 10000, e);
        assertTrue(g.getEvenements().isEmpty());
        assertTrue(e.getVentes().isEmpty());
        g.ajouterExposition(e);
        assertFalse(g.getEvenements().isEmpty());
        e.ajouterTransaction(t);
        assertFalse(e.getVentes().isEmpty());
        assertEquals(10000, g.CAannuel(LocalDate.now().getYear()));
    }
    
    @Test
    //Test permettant de tester la méthode CA() de Exposition
    public void calculerCA(){
        Galerie g = new Galerie(1, "Nom", "Adresse");
        Exposition e = new Exposition(1, "Intitule", 1, g);
        Transaction t = new Transaction(1, LocalDate.now(), 10000, e);
        assertTrue(e.getVentes().isEmpty());
        e.ajouterTransaction(t);
        assertFalse(e.getVentes().isEmpty());
        assertEquals(10000, e.CA());
    }
    
    @Test 
    //Test permettant de tester la méthode budgetArt() de Personne
    public void calculerBudgetArt(){
        Personne p = new Personne("Nom", "Adresse");
        Transaction t = new Transaction(1, LocalDate.now(), 10000, p);
        assertTrue(p.getAchats().isEmpty());
        p.ajouterTransaction(t);
        assertFalse(p.getAchats().isEmpty());
        assertEquals(10000, p.budgetArt(LocalDate.now().getYear()));
    }
  /*
    @Test
    @Sql("test-data.sql")
    public void onNePeutPasDetruireUneGalerieQuiADesExpositions() {
	log.info("Détruire une galerie avec des expositions");
	Galerie saatchi = galerieDAO.getOne(1);
	assertEquals("Saatchi", saatchi.getNom());
        // On crée une nouvelle exposition
        Exposition expo = new Exposition();
        expo.setDebut(LocalDate.of(2020, Month.MARCH, 15));
        expo.setIntitule("Exposition");
        expo.setDuree(1);
        
        // On ajoute l'exposition aux événements de la galerie
        // saatchi.evenements.add(expo);
        saatchi.getEvenements().add(expo);
        // Il y a des expositions dans la galerie 'Saatchi'
	assertFalse(saatchi.getEvenements().isEmpty());
	// Si on essaie de détruire cette galerie, on doit avoir une exception
	// de violation de contrainte d'intégrité
	assertThrows(DataIntegrityViolationException.class, () -> {
            galerieDAO.delete(saatchi);
            galerieDAO.flush(); // Pour forcer la validation de la transaction
	});
    }
    */

}
