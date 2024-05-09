package tn.esprit.rh.achat.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategorieProduitControllerTest {

    @Mock
    private ICategorieProduitService categorieProduitService;

    @InjectMocks
    private CategorieProduitController categorieProduitController;

    private MockMvc mockMvc;

    public CategorieProduitControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categorieProduitController).build();
    }

    @Test
    public void testGetCategorieProduit() throws Exception {
        CategorieProduit cp1 = new CategorieProduit(1L, "Cat1", "Description1");
        CategorieProduit cp2 = new CategorieProduit(2L, "Cat2", "Description2");

        List<CategorieProduit> categorieProduits = Arrays.asList(cp1, cp2);
        when(categorieProduitService.retrieveAllCategorieProduits()).thenReturn(categorieProduits);

        mockMvc.perform(get("/categorieProduit/retrieve-all-categorieProduit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testRetrieveCategorieProduit() throws Exception {
        CategorieProduit cp = new CategorieProduit(1L, "Cat1", "Description1");
        when(categorieProduitService.retrieveCategorieProduit(1L)).thenReturn(cp);

        mockMvc.perform(get("/categorieProduit/retrieve-categorieProduit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Cat1"));
    }

    @Test
    public void testAddCategorieProduit() throws Exception {
        CategorieProduit cp = new CategorieProduit(1L, "Cat1", "Description1");
        when(categorieProduitService.addCategorieProduit(any(CategorieProduit.class))).thenReturn(cp);

        String jsonBody = "{ \"id\": 1, \"nom\": \"Cat1\", \"description\": \"Description1\" }";

        mockMvc.perform(post("/categorieProduit/add-categorieProduit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Cat1"));
    }

    @Test
    public void testRemoveCategorieProduit() throws Exception {
        doNothing().when(categorieProduitService).deleteCategorieProduit(1L);

        mockMvc.perform(delete("/categorieProduit/remove-categorieProduit/1"))
                .andExpect(status().isOk());

        verify(categorieProduitService, times(1)).deleteCategorieProduit(1L);
    }

    @Test
    public void testModifyCategorieProduit() throws Exception {
        CategorieProduit updatedCp = new CategorieProduit(1L, "CatUpdated", "Updated Description");
        when(categorieProduitService.updateCategorieProduit(any(CategorieProduit.class))).thenReturn(updatedCp);

        String jsonBody = "{ \"id\": 1, \"nom\": \"CatUpdated\", \"description\": \"Updated Description\" }";

        mockMvc.perform(put("/categorieProduit/modify-categorieProduit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("CatUpdated"));
    }
}
