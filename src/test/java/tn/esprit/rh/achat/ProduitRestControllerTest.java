package tn.esprit.rh.achat.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProduitRestControllerTest {

    @Mock
    private IProduitService produitService;

    @InjectMocks
    private ProduitRestController produitRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produitRestController).build();
    }

    @Test
    void testRetrieveAllProduits() throws Exception {
        // Créez une liste de produits de test
        Produit p1 = new Produit(1L, "Produit1", 10.0, 100);
        Produit p2 = new Produit(2L, "Produit2", 20.0, 200);

        // Simulez le comportement du service
        when(produitService.retrieveAllProduits()).thenReturn(Arrays.asList(p1, p2));

        // Testez la requête GET /retrieve-all-produits
        mockMvc.perform(get("/produit/retrieve-all-produits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Produit1"))
                .andExpect(jsonPath("$[1].nom").value("Produit2"));
    }

    @Test
    void testRetrieveProduit() throws Exception {
        // Créez un produit de test
        Long produitId = 1L;
        Produit p = new Produit(produitId, "Produit1", 10.0, 100);

        // Simulez le comportement du service
        when(produitService.retrieveProduit(produitId)).thenReturn(p);

        // Testez la requête GET /retrieve-produit/{produit-id}
        mockMvc.perform(get("/produit/retrieve-produit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Produit1"));
    }

    @Test
    void testAddProduit() throws Exception {
        // Créez un produit de test
        Produit p = new Produit(1L, "Produit1", 10.0, 100);

        // Simulez le comportement du service
        when(produitService.addProduit(any(Produit.class))).thenReturn(p);

        String requestBody = "{ \"nom\": \"Produit1\", \"prix\": 10.0, \"quantite\": 100 }";

        // Testez la requête POST /add-produit
        mockMvc.perform(post("/produit/add-produit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Produit1"));
    }

    @Test
    void testRemoveProduit() throws Exception {
        Long produitId = 1L;

        // Simulez la suppression
        doNothing().when(produitService).deleteProduit(produitId);

        // Testez la requête DELETE /remove-produit/{produit-id}
        mockMvc.perform(delete("/produit/remove-produit/1"))
                .andExpect(status().isOk());

        // Vérifiez que la méthode de suppression a été appelée
        verify(produitService, times(1)).deleteProduit(produitId);
    }

    @Test
    void testModifyProduit() throws Exception {
        // Créez un produit mis à jour
        Produit updatedProduit = new Produit(1L, "ProduitUpdated", 15.0, 150);

        // Simulez le comportement du service
        when(produitService.updateProduit(any(Produit.class))).thenReturn(updatedProduit);

        String requestBody = "{ \"id\": 1, \"nom\": \"ProduitUpdated\", \"prix\": 15.0, \"quantite\": 150 }";

        // Testez la requête PUT /modify-produit
        mockMvc.perform(put("/produit/modify-produit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("ProduitUpdated"));
    }
}
