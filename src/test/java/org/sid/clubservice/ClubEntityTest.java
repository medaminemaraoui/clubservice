package org.sid.clubservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sid.clubservice.controller.ClubController;
import org.sid.clubservice.entity.Club;
import org.sid.clubservice.repository.ClubRepository;
import org.sid.clubservice.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClubEntityTest {

    @Test
    void testClubEntity() {
        // Arrange
        Club club = new Club();
        club.setId(1L);
        club.setName("Test Club");
        club.setDescription("Test Description");

        // Assert
        assertEquals(1L, club.getId());
        assertEquals("Test Club", club.getName());
        assertEquals("Test Description", club.getDescription());
    }

    @Test
    void testClubEqualsAndHashCode() {
        // Arrange
        Club club1 = new Club();
        club1.setId(1L);
        club1.setName("Test Club");

        Club club2 = new Club();
        club2.setId(1L);
        club2.setName("Test Club");

        // Assert
        assertEquals(club1, club2);
        assertEquals(club1.hashCode(), club2.hashCode());
    }

    @Test
    void testClubToString() {
        // Arrange
        Club club = new Club();
        club.setId(1L);
        club.setName("Test Club");
        club.setDescription("Test Description");

        // Act
        String toString = club.toString();

        // Assert
        assertTrue(toString.contains("Test Club"));
        assertTrue(toString.contains("Test Description"));
    }
}