package com.lpiot.ouila;

import com.lpiot.ouila.resources.LessonResource;
import com.lpiot.ouila.services.LessonService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = LessonResource.class)
public class LessonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonService lessonService;

    @Test
    @DisplayName("Should List All Lessons")
    public void testGetLessons() throws Exception {
        mockMvc.perform(get("/lessons")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));
        // .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        // .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name")))
        // .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site")));
    }

    @Test
    @DisplayName("Should Create A Lesson")
    public void testPostLesson() throws Exception {
        mockMvc.perform(post("/lessons")).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));
        // .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        // .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name")))
        // .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site")));
    }

    @Test
    @DisplayName("Should Delete A Lesson")
    public void testDeleteLesson() throws Exception {
        mockMvc.perform(delete("/lessons/1")).andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        // .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        // .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name")))
        // .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site")));
    }
}