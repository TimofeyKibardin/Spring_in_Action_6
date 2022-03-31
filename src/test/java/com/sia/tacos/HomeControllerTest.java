package com.sia.tacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*@WebMvcTest(HomeController.class) //WEB test for home controller
public class HomeControllerTest {

    @Autowired //Injects MockMVC
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")) //Performs GET
                .andExpect(status().isOk()) //Expects HTTP 200 code
                .andExpect(view().name("home")) //Expects home view
                .andExpect(content().string(containsString("Welcome to..."))); //Expects Welcome to...
    }
}*/
