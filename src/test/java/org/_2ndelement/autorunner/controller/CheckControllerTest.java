package org._2ndelement.autorunner.controller;

import org._2ndelement.autorunner.entity.User;
import org._2ndelement.autorunner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CheckControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final User user = new User();

    static {
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
    }

    @Test
    @Rollback
    void existUsername() throws Exception {
        when(userService.exist(eq("username"), eq(user.getUsername()))).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/check/username/{username}", user.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(false));
        userService.save(user);
        when(userService.exist(eq("username"), eq(user.getUsername()))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/check/username/{username}", user.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(true));
    }

    @Test
    @Rollback
    void existEmail() throws Exception {
        when(userService.exist(eq("email"), eq(user.getEmail()))).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/check/email/{email}", user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(false));
        userService.save(user);
        when(userService.exist(eq("email"), eq(user.getEmail()))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/check/email/{email}", user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(true));
    }

}
