package com.moj.anagram;

import com.moj.anagram.misc.StandaloneMvcTestViewResolver;
import com.moj.anagram.service.AnagramController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnagramController.class)
public class AnagramControllerTest {

    @Autowired
    private AnagramController controller;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(new StandaloneMvcTestViewResolver()).build();
    }

    @Test
    public void testAppleReturnsMultipleMatches() throws Exception {
        mvc.perform(get("/apple")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].apple", iterableWithSize(4)));
    }

    @Test
    public void testNoMatchForFoo() throws Exception {
        mvc.perform(get("/total_foo_")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].total_foo_", iterableWithSize(0)));
    }
}