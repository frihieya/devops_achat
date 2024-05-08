package tn.esprit.rh.achat.tests;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

@ExtendWith(MockitoExtension.class)
public class CategorieProduitControllerTest {

    @Mock
    private ICategorieProduitService categorieProduitService;

    @InjectMocks
    private CategorieProduitController categorieProduitController;

    private MockMvc mockMvc;

    @Test
    public void testGetCategorieProduit() throws Exception {
        // Arrange
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        // Add some sample data to the list, e.g., using Mockito's mock objects.
        when(categorieProduitService.retrieveAllCategorieProduits()).thenReturn(categorieProduits);

        // Act & Assert
        mockMvc.perform(get("/categorieProduit/retrieve-all-categorieProduit"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Add more assertions based on your response expectations.
                .andExpect(jsonPath("$", hasSize(0))); // Example assertion, change as per your data.
    }

    // Add similar test methods for other controller methods like retrieveCategorieProduit, addCategorieProduit, etc.

    // You may need to add tests for error scenarios, validation, etc., depending on your requirements.

}
