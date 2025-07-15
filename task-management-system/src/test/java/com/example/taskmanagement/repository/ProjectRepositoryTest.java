import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.repository.ProjectRepository;

import java.util.Optional;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project();
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate(new java.util.Date());
        project.setEndDate(new java.util.Date());
        project.setStatus("ACTIVE");
        projectRepository.save(project);
    }

    @Test
    public void testFindById() {
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertNotNull(foundProject);
        assertEquals(project.getName(), foundProject.get().getName());
    }

    @Test
    public void testSaveProject() {
        Project newProject = new Project();
        newProject.setName("New Project");
        newProject.setDescription("New Description");
        newProject.setStartDate(new java.util.Date());
        newProject.setEndDate(new java.util.Date());
        newProject.setStatus("ACTIVE");

        Project savedProject = projectRepository.save(newProject);
        assertNotNull(savedProject.getId());
        assertEquals(newProject.getName(), savedProject.getName());
    }

    @Test
    public void testDeleteProject() {
        projectRepository.delete(project);
        Optional<Project> deletedProject = projectRepository.findById(project.getId());
        assertEquals(Optional.empty(), deletedProject);
    }
}