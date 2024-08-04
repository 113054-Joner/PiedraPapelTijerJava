package com.example.demo.controllers;

import com.example.demo.entities.PlayerEntity;
import com.example.demo.models.Player;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.demo.repositories.jpa.PlayerJpaRepository;
import com.example.demo.services.PlayerServices;
import com.example.demo.services.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerServices playerServices;

    @Mock
    private PlayerJpaRepository playerJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private PlayerServiceImpl playerServiceImpl;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getByIdTest() throws Exception {
        // given
        Player player = new Player();
        player.setId(2L);
        player.setEmail("test@test.com");
        player.setUserName("PlayerTest");
        player.setPassword("Password01#");
        // when
         when(playerServices.getPlayerById(2L)).thenReturn(player);
//      when(playerServices.greet()).thenReturn("Hello, Mock");
//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")));
//        // then
        //VALIDA FORMA 1
        this.mockMvc.perform(get("/players/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("PlayerTest"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.password").value("Password01#"))
        ;

        //VALIDA OBJETO FORMA 2
        MvcResult mvcResult = this.mockMvc.perform(get("/players/2")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        Player result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Player.class);

        Assertions.assertEquals("PlayerTest", result.getUserName());
    }

    @Test
    void savePlayerTest() throws Exception {
        //given
        Player player = new Player();
        player.setEmail("test@test.com");
        player.setUserName("PlayerTest");
        player.setPassword("Password01#");

        //When
        when(playerServices.savePlayer(any(Player.class))).thenReturn(player);


        // FORMA 2
        MvcResult mvcResult = this.mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player)))  // Enviar el objeto Player como JSON
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        Player result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Player.class);

        Assertions.assertEquals("PlayerTest", result.getUserName());

        //VALIDA SI ES OK O NO
//        this.mockMvc.perform(post("/players")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(player)))  // Enviar el objeto Player como JSON
//                .andDo(print())
//                .andExpect(status().isOk());

    }


    @Test
    public void getByIdTest2() throws Exception {
        // Crea una instancia de PlayerEntity simulada
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(2L);
        playerEntity.setEmail("test@test.com");
        playerEntity.setUserName("PlayerTest");
        playerEntity.setPassword("Password01#");

        // Crea la instancia Player que deseas que se mapee desde playerEntity
        Player player = new Player();
        player.setId(2L);
        player.setEmail("test@test.com");
        player.setUserName("PlayerTest");
        player.setPassword("Password01#");

        // Configura el mock para devolver playerEntity cuando se llame a getReferenceById
        when(playerJpaRepository.getReferenceById(2L)).thenReturn(playerEntity);

        // Configura el mock de modelMapper para devolver el Player cuando se mapea el PlayerEntity
        when(modelMapper.map(playerEntity, Player.class)).thenReturn(player);

        // Ahora llama al servicio para obtener el Player mapeado desde el PlayerEntity
        Player playerMapped = playerServiceImpl.getPlayerById(2L);

        // Verifica que los datos en el objeto Player son los esperados
        Assertions.assertEquals("test@test.com", playerMapped.getEmail());
        Assertions.assertEquals("PlayerTest", playerMapped.getUserName());
        Assertions.assertEquals("Password01#", playerMapped.getPassword());

        // Verifica que los datos en el objeto Player son los esperados
    }

}