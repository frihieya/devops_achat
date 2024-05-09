package tn.esprit.rh.achat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.rh.achat.controllers.OperateurController;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OperateurController.class)
public class OperateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOperateurService operateurService;

    @Test
    public void testGetOperateurs() throws Exception {
        mockMvc.perform(get("/operateur/retrieve-all-operateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testRetrieveOperateur() throws Exception {
        Long operateurId = 1L;
        mockMvc.perform(get("/operateur/retrieve-operateur/{operateur-id}", operateurId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testAddOperateur() throws Exception {
        Operateur operateur = new Operateur(); // Initialize with necessary properties
        mockMvc.perform(post("/operateur/add-operateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(operateur)))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testRemoveOperateur() throws Exception {
        Long operateurId = 1L;
        mockMvc.perform(delete("/operateur/remove-operateur/{operateur-id}", operateurId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testModifyOperateur() throws Exception {
        Operateur operateur = new Operateur(); // Initialize with necessary properties
        mockMvc.perform(put("/operateur/modify-operateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(operateur)))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
