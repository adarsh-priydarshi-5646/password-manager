package ua.com.javarush.parse.m5.passwordmanager.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;
import ua.com.javarush.parse.m5.passwordmanager.service.VaultItemService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VaultItemController.class)
@Import(VaultItemControllerTest.TestConfig.class)
class VaultItemControllerTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public VaultItemService vaultItemService() {
            return mock(VaultItemService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VaultItemService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenPost_thenCreateItem() throws Exception {
        VaultItem item = new VaultItem();
        item.setId(1L);
        when(service.save(any(VaultItem.class))).thenReturn(item);

        mockMvc.perform(post("/api/v1/vault/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void whenGetById_thenReturnsItem() throws Exception {
        VaultItem item = new VaultItem();
        item.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/v1/vault/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void whenPut_thenUpdatesItem() throws Exception {
        VaultItem item = new VaultItem();
        item.setId(1L);
        when(service.update(anyLong(), any(VaultItem.class))).thenReturn(Optional.of(item));

        mockMvc.perform(put("/api/v1/vault/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void whenGetByLogin_thenReturnsItems() throws Exception {
        VaultItem item = new VaultItem();
        item.setLogin("testuser");
        when(service.findByLogin("testuser")).thenReturn(List.of(item));

        mockMvc.perform(get("/api/v1/vault").param("login", "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("testuser"));
    }

    @Test
    void whenGetAll_thenReturnsAllItems() throws Exception {
        VaultItem item1 = new VaultItem();
        VaultItem item2 = new VaultItem();
        when(service.findAll()).thenReturn(List.of(item1, item2));

        mockMvc.perform(get("/api/v1/vault/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void whenDelete_thenReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/v1/vault/{id}", 1L))
                .andExpect(status().isOk());
    }
}
