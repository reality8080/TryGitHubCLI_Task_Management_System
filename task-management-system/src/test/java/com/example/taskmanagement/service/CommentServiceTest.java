import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.repository.CommentRepository;
import com.example.taskmanagement.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(CommentService.class)
public class CommentServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        comment = new Comment();
        comment.setId(1L);
        comment.setContent("This is a test comment");
        comment.setAuthor(null); // Set author as needed
        comment.setTask(null); // Set task as needed
    }

    @Test
    public void testCreateComment() throws Exception {
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"This is a test comment\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("This is a test comment"));
    }

    @Test
    public void testGetCommentById() throws Exception {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        mockMvc.perform(get("/api/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("This is a test comment"));
    }

    @Test
    public void testGetAllComments() throws Exception {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment));

        mockMvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("This is a test comment"));
    }

    @Test
    public void testUpdateComment() throws Exception {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(put("/api/comments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"Updated comment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated comment"));
    }

    @Test
    public void testDeleteComment() throws Exception {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        mockMvc.perform(delete("/api/comments/1"))
                .andExpect(status().isNoContent());
    }
}