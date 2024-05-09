package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.rh.achat.controllers.FactureRestController;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.IFactureService;

class FactureRestControllerTest {

    @Mock
    private IFactureService factureService;

    @InjectMocks
    private FactureRestController factureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFactures() {
        // Given
        List<Facture> factures = new ArrayList<>();
        factures.add(new Facture());
        factures.add(new Facture());
        when(factureService.retrieveAllFactures()).thenReturn(factures);

        // When
        List<Facture> result = factureController.getFactures();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveFacture() {
        // Given
        Long factureId = 1L;
        Facture facture = new Facture();
        when(factureService.retrieveFacture(factureId)).thenReturn(facture);

        // When
        Facture result = factureController.retrieveFacture(factureId);

        // Then
        assertNotNull(result);
        assertEquals(facture, result);
    }

    @Test
    void testAddFacture() {
        // Given
        Facture factureToAdd = new Facture();
        when(factureService.addFacture(factureToAdd)).thenReturn(factureToAdd);

        // When
        Facture result = factureController.addFacture(factureToAdd);

        // Then
        assertNotNull(result);
        assertEquals(factureToAdd, result);
    }
}
