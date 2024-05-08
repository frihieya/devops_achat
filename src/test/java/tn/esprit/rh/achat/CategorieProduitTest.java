package tn.esprit.rh.achat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

class CategorieProduitTest {

    @Mock
    CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllCategorieProduits() {
        // Given
        List<CategorieProduit> categories = new ArrayList<>();
        categories.add(new CategorieProduit());
        categories.add(new CategorieProduit());
        when(categorieProduitRepository.findAll()).thenReturn(categories);

        // When
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Given
        Long categoryId = 1L;
        CategorieProduit category = new CategorieProduit();
        when(categorieProduitRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // When
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(categoryId);

        // Then
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void testAddCategorieProduit() {
        // Given
        CategorieProduit categoryToAdd = new CategorieProduit();
        when(categorieProduitRepository.save(categoryToAdd)).thenReturn(categoryToAdd);

        // When
        CategorieProduit result = categorieProduitService.addCategorieProduit(categoryToAdd);

        // Then
        assertNotNull(result);
        assertEquals(categoryToAdd, result);
    }
}
