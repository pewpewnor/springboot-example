package com.example.projectbackend;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.helper.TokenGeneratorHelper;
import com.example.projectbackend.repository.UserRepository;
import com.example.projectbackend.web.model.UserRole;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
  @Autowired private MockMvc mockMvc;
  // replaces UserRepository implementation with this which is an @Mock
  // @Mock's method and attribute can be altered to pretend what the output is
  @MockBean private UserRepository userRepository;

  private User customer =
      new User("customerTest", "test", UserRole.CUSTOMER, TokenGeneratorHelper.generateToken());
  private User admin =
      new User("adminTest", "test", UserRole.ADMIN, TokenGeneratorHelper.generateToken());

  @Test
  public void getAllUsers_WithValidRequest_ReturnsAllUsers() throws Exception {
    // instead of anyString() it could just be the real string itself admin.getToken()
    Mockito.when(userRepository.findByToken(Mockito.anyString())).thenReturn(Optional.of(admin));
    Mockito.when(userRepository.findAll()).thenReturn(List.of(admin, customer));
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/users")
                .header("Authorization", "Bearer " + admin.getToken()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
  }

  @Test
  public void getAllUsers_WithIncorrectCredential_ReturnsStatusForbidden() throws Exception {
    Mockito.when(userRepository.findByToken(Mockito.anyString())).thenReturn(Optional.empty());
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/users")
                .header("Authorization", "Bearer " + admin.getToken()))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }
}
