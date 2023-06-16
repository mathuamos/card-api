package com.card.cardapi.repositories;

import com.card.cardapi.entities.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RoleRepoTest {

    @Autowired
    private RoleRepo roleRepository;

    @Test
    public void testFindByName() {
        // Create a test role
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN_TEST");

        // Save the role to the database
        roleRepository.save(role);

        // Call the findByName method
        Role result = roleRepository.findByName("ROLE_ADMIN_TEST");

        // Assert the expected result
        assertNotNull(result);
        assertEquals("ROLE_ADMIN_TEST", result.getName());
    }

}