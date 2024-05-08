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

class FournisseurRestControllerTest {

    @Mock
    private IFournisseurService fournisseurService;

    @InjectMocks
    private FournisseurRestController fournisseurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFournisseurs() {
        // Given
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur());
        fournisseurs.add(new Fournisseur());
        when(fournisseurService.retrieveAllFournisseurs()).thenReturn(fournisseurs);

        // When
        List<Fournisseur> result = fournisseurController.getFournisseurs();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveFournisseur() {
        // Given
        Long fournisseurId = 1L;
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurService.retrieveFournisseur(fournisseurId)).thenReturn(fournisseur);

        // When
        Fournisseur result = fournisseurController.retrieveFournisseur(fournisseurId);

        // Then
        assertNotNull(result);
        assertEquals(fournisseur, result);
    }

    @Test
    void testAddFournisseur() {
        // Given
        Fournisseur fournisseurToAdd = new Fournisseur();
        when(fournisseurService.addFournisseur(fournisseurToAdd)).thenReturn(fournisseurToAdd);

        // When
        Fournisseur result = fournisseurController.addFournisseur(fournisseurToAdd);

        // Then
        assertNotNull(result);
        assertEquals(fournisseurToAdd, result);
    }
}
