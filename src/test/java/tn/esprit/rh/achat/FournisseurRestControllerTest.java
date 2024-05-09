package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.rh.achat.controllers.FournisseurRestController;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.IFournisseurService;

public class FournisseurRestControllerTest {

    @Mock
    private IFournisseurService fournisseurService;  // Simuler le service

    @InjectMocks
    private FournisseurRestController fournisseurRestController;  // Injecter le contrôleur avec les objets simulés

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les objets simulés
    }

    @Test
    void testGetFournisseurs() {
        // Préparation des données simulées
        List<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur fournisseur1 = new Fournisseur(1L, "Fournisseur A");
        Fournisseur fournisseur2 = new Fournisseur(2L, "Fournisseur B");
        fournisseurs.add(fournisseur1);
        fournisseurs.add(fournisseur2);

        when(fournisseurService.retrieveAllFournisseurs()).thenReturn(fournisseurs);  // Simulation du service

        // Appel de la méthode du contrôleur
        List<Fournisseur> result = fournisseurRestController.getFournisseurs();

        // Assertions pour valider les résultats
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals(2, result.size());  // Vérifier le nombre d'éléments
        assertEquals("Fournisseur A", result.get(0).getNom());  // Vérifier les noms des fournisseurs
        assertEquals("Fournisseur B", result.get(1).getNom());
    }

    @Test
    void testRetrieveFournisseur() {
        // Préparation des données simulées
        Long fournisseurId = 1L;
        Fournisseur expectedFournisseur = new Fournisseur(fournisseurId, "Fournisseur A");
        when(fournisseurService.retrieveFournisseur(fournisseurId)).thenReturn(expectedFournisseur);  // Simulation du service

        // Appel de la méthode du contrôleur
        Fournisseur result = fournisseurRestController.retrieveFournisseur(fournisseurId);

        // Assertions pour valider le résultat
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals("Fournisseur A", result.getNom());  // Vérifier que le nom correspond
    }

    @Test
    void testAddFournisseur() {
        // Préparation des données simulées
        Fournisseur newFournisseur = new Fournisseur(3L, "Fournisseur C");
        when(fournisseurService.addFournisseur(newFournisseur)).thenReturn(newFournisseur);  // Simulation du service

        // Appel de la méthode du contrôleur
        Fournisseur result = fournisseurRestController.addFournisseur(newFournisseur);

        // Assertions pour valider le résultat
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals("Fournisseur C", result.getNom());  // Vérifier le nom du nouveau fournisseur
    }

    @Test
    void testRemoveFournisseur() {
        // Préparation des données simulées
        Long fournisseurId = 1L;

        // Appel de la méthode du contrôleur
        fournisseurRestController.removeFournisseur(fournisseurId);

        // Vérification que le service a été appelé
        verify(fournisseurService, times(1)).deleteFournisseur(fournisseurId);  // Vérifier que le service a été appelé
    }

    @Test
    void testModifyFournisseur() {
        // Préparation des données simulées
        Fournisseur updatedFournisseur = new Fournisseur(1L, "Fournisseur Modifié");
        when(fournisseurService.updateFournisseur(updatedFournisseur)).thenReturn(updatedFournisseur);  // Simulation du service

        // Appel de la méthode du contrôleur
        Fournisseur result = fournisseurRestController.modifyFournisseur(updatedFournisseur);

        // Assertions pour valider le résultat
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals("Fournisseur Modifié", result.getNom());  // Vérifier le nom du fournisseur modifié
    }

    @Test
    void testAssignSecteurActiviteToFournisseur() {
        // Préparation des données simulées
        Long idSecteurActivite = 1L;
        Long idFournisseur = 1L;

        // Appel de la méthode du contrôleur
        fournisseurRestController.assignProduitToStock(idSecteurActivite, idFournisseur);

        // Vérification que le service a été appelé
        verify(fournisseurService, times(1)).assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);  // Vérifier que le service a été appelé
    }
}
