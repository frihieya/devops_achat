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

import tn.esprit.rh.achat.controllers.ProduitRestController;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

class ProduitRestControllerTest {

    @Mock
    private IProduitService produitService;

    @InjectMocks
    private ProduitRestController produitRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les objets Mock
    }

    @Test
    void testGetProduits() {
        // Given
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit(1L, "Produit A"));
        produits.add(new Produit(2L, "Produit B"));
        when(produitService.retrieveAllProduits()).thenReturn(produits);  // Simuler la réponse du service

        // When
        List<Produit> result = produitRestController.getProduits();  // Appeler la méthode du contrôleur

        // Then
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals(2, result.size());  // S'assurer que le nombre d'éléments est correct
        assertEquals("Produit A", result.get(0).getNomProduit());  // Vérifier les valeurs attendues
        assertEquals("Produit B", result.get(1).getNomProduit());
    }

    @Test
    void testRetrieveProduit() {
        // Given
        Long produitId = 1L;
        Produit expectedProduit = new Produit(produitId, "Produit A");
        when(produitService.retrieveProduit(produitId)).thenReturn(expectedProduit);  // Simuler le service

        // When
        Produit result = produitRestController.retrieveRayon(produitId);  // Appeler la méthode du contrôleur

        // Then
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals(expectedProduit, result);  // Vérifier que le produit récupéré est le bon
    }

    @Test
    void testAddProduit() {
        // Given
        Produit newProduit = new Produit(3L, "Produit C");
        when(produitService.addProduit(newProduit)).thenReturn(newProduit);  // Simuler le service

        // When
        Produit result = produitRestController.addProduit(newProduit);  // Appeler la méthode du contrôleur

        // Then
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals("Produit C", result.getNomProduit());  // Vérifier le nom du produit ajouté
    }

    @Test
    void testRemoveProduit() {
        // Given
        Long produitId = 1L;

        // When
        produitRestController.removeProduit(produitId);  // Appeler la méthode du contrôleur

        // Then
        verify(produitService, times(1)).deleteProduit(produitId);  // S'assurer que le service est appelé
    }

    @Test
    void testModifyProduit() {
        // Given
        Produit updatedProduit = new Produit(1L, "Produit Modifié");
        when(produitService.updateProduit(updatedProduit)).thenReturn(updatedProduit);  // Simuler le service

        // When
        Produit result = produitRestController.modifyProduit(updatedProduit);  // Appeler la méthode du contrôleur

        // Then
        assertNotNull(result);  // S'assurer que le résultat n'est pas nul
        assertEquals("Produit Modifié", result.getNomProduit());  // Vérifier le nom du produit modifié
    }
}
