package tn.esprit.rh.achat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.rh.achat.controllers.ProduitRestController;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProduitRestController.class)
public class ProduitRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProduitService produitService;

    @Test
    public void testGetProduits() throws Exception {
        // Prepare mock data
        List<Produit> produits = new ArrayList<>();
        // Add some sample products to the list

        // Mock the service method
        when(produitService.retrieveAllProduits()).thenReturn(produits);

        // Perform the GET request
        mockMvc.perform(get("/produit/retrieve-all-produits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Add more assertions as needed
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRetrieveProduit() throws Exception {
        // Prepare mock data
        Long produitId = 1L;
        Produit produit = new Produit();
        // Set necessary properties for produit

        // Mock the service method
        when(produitService.retrieveProduit(produitId)).thenReturn(produit);

        // Perform the GET request
        mockMvc.perform(get("/produit/retrieve-produit/{produit-id}", produitId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Add more assertions as needed
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
