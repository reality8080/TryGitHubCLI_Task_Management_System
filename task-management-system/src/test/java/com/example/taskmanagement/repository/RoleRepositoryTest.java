import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.taskmanagement.entity.Role;
import com.example.taskmanagement.repository.RoleRepository;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);
    }

    @Test
    public void testFindByName() {
        Role role = roleRepository.findByName("USER");
        assertNotNull(role);
        assertEquals("USER", role.getName());
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setName("ADMIN");
        Role savedRole = roleRepository.save(role);
        assertNotNull(savedRole.getId());
    }

    @Test
    public void testDeleteRole() {
        Role role = roleRepository.findByName("USER");
        roleRepository.delete(role);
        Role deletedRole = roleRepository.findByName("USER");
        assertEquals(null, deletedRole);
    }
}