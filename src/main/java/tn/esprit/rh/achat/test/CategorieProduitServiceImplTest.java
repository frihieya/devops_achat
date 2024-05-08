package tn.esprit.rh.achat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CategorieProduitServiceImplTest {

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
        assert !result.isEmpty();
        assert result.size() == 2;
    }

    @Test
    void testAddCategorieProduit() {
        // Given
        CategorieProduit categorieProduit = new CategorieProduit();

        // When
        categorieProduitService.addCategorieProduit(categorieProduit);

        // Then
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    void testDeleteCategorieProduit() {
        // Given
        Long categorieId = 1L;

        // When
        categorieProduitService.deleteCategorieProduit(categorieId);

        // Then
        verify(categorieProduitRepository, times(1)).deleteById(categorieId);
    }

    @Test
    void testUpdateCategorieProduit() {
        // Given
        CategorieProduit categorieProduit = new CategorieProduit();

        // When
        categorieProduitService.updateCategorieProduit(categorieProduit);

        // Then
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Given
        Long categorieId = 1L;
        CategorieProduit categorieProduit = new CategorieProduit();
        when(categorieProduitRepository.findById(categorieId)).thenReturn(Optional.of(categorieProduit));

        // When
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(categorieId);

        // Then
        assert result != null;
    }
}