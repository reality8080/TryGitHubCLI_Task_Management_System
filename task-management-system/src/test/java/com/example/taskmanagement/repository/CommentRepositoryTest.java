import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CommentRepositoryTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private Comment comment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Comment comment = new Comment();
        comment.setId(1L);
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        Optional<Comment> foundComment = commentRepository.findById(1L);
        assertTrue(foundComment.isPresent());
        assertEquals(comment.getId(), foundComment.get().getId());
    }

    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setContent("Test comment");
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment savedComment = commentRepository.save(comment);
        assertNotNull(savedComment);
        assertEquals("Test comment", savedComment.getContent());
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        comment.setId(1L);
        doNothing().when(commentRepository).delete(comment);

        commentRepository.delete(comment);
        verify(commentRepository, times(1)).delete(comment);
    }
}