package io.swagger.controller;

import io.swagger.model.Comment;
import io.swagger.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void testGetComment() throws Exception {
        UUID id = UUID.randomUUID();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setAuthor("Alice Johnson");
        comment.setText("This is a sample comment.");

        when(commentService.getComment(id)).thenReturn(comment);

        mockMvc.perform(get("/comments/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.author").value("Alice Johnson"))
                .andExpect(jsonPath("$.text").value("This is a sample comment."));
    }

    @Test
    public void testCreateComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID());
        comment.setAuthor("Bob Smith");
        comment.setText("Newly created comment content.");

        when(commentService.createComment(anyString(), anyString())).thenReturn(comment);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Bob Smith\", \"text\":\"Newly created comment content.\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author").value("Bob Smith"))
                .andExpect(jsonPath("$.text").value("Newly created comment content."));
    }

    @Test
    public void testDeleteComment() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(commentService).deleteComment(id);

        mockMvc.perform(delete("/comments/" + id))
                .andExpect(status().isNoContent());
    }
}