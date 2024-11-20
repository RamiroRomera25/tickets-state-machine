package rami.generic.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import rami.generic.services.DummyService;


//@WebMvcTest(DummyController.class)
@SpringBootTest
@AutoConfigureMockMvc
class DummyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DummyService dummyService;

    @Autowired
    private ObjectMapper objectMapper;


    /*
     this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("The player id 1 do not exist"))
    */

}