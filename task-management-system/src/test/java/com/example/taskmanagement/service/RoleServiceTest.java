import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.taskmanagement.entity.Role;
import com.example.taskmanagement.repository.RoleRepository;
import com.example.taskmanagement.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRoles() {
        Role role1 = new Role(1L, "ADMIN");
        Role role2 = new Role(2L, "USER");
        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleService.findAllRoles();

        assertEquals(2, roles.size());
        assertEquals("ADMIN", roles.get(0).getName());
        assertEquals("USER", roles.get(1).getName());
    }

    @Test
    public void testFindRoleById() {
        Role role = new Role(1L, "ADMIN");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role foundRole = roleService.findRoleById(1L);

        assertNotNull(foundRole);
        assertEquals("ADMIN", foundRole.getName());
    }

    @Test
    public void testCreateRole() {
        Role role = new Role(null, "USER");
        when(roleRepository.save(role)).thenReturn(new Role(1L, "USER"));

        Role createdRole = roleService.createRole(role);

        assertNotNull(createdRole.getId());
        assertEquals("USER", createdRole.getName());
    }

    @Test
    public void testUpdateRole() {
        Role existingRole = new Role(1L, "USER");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(existingRole)).thenReturn(existingRole);

        Role updatedRole = roleService.updateRole(1L, existingRole);

        assertEquals("USER", updatedRole.getName());
    }

    @Test
    public void testDeleteRole() {
        Role role = new Role(1L, "USER");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(role);

        roleService.deleteRole(1L);

        verify(roleRepository, times(1)).delete(role);
    }
}