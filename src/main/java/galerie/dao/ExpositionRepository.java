/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Exposition;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Salomé Chevailler
 */
public interface ExpositionRepository extends JpaRepository<Exposition, Integer>{
    /**
     * Calculer le chiffre d'affaires pour une exposition
     * @param id la clé primaire de l'exposition
     * @return le chiffre d'affaires de cette exposition
     */
    @Query(
        value =
        "SELECT SUM(prix_vente) AS budget FROM Exposition" + 
        "INNER JOIN Transaction ON Transaction.exposition_id = Exposition.id" + 
        "WHERE Exposition.id = :id GROUP BY Exposition.id", 
        nativeQuery = true
    )
    public float chiffreAffairePour(int id);
}


