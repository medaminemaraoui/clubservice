package org.sid.clubservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sid.clubservice.controller.ClubController;
import org.sid.clubservice.entity.Club;
import org.sid.clubservice.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

class ClubControllerTest {

    @Mock
    private ClubService clubService;

    @InjectMocks
    private ClubController clubController;

    private Club testClub;
    private List<Club> testClubs;

    @BeforeEach
    void setUp() {
        testClub = new Club();
        testClub.setId(1L);
        testClub.setName("Test Club");
        testClub.setDescription("Test Description");

        Club club2 = new Club();
        club2.setId(2L);
        club2.setName("Test Club 2");
        club2.setDescription("Test Description 2");

        testClubs = Arrays.asList(testClub, club2);
    }

    @Test
    void getAllClubs_ShouldReturnAllClubs() {
        // Arrange
        when(clubService.getAllClubs()).thenReturn(testClubs);

        // Act
        ResponseEntity<List<Club>> response = clubController.getAllClubs();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getClubById_WhenClubExists_ShouldReturnClub() {
        // Arrange
        when(clubService.getClubById(1L)).thenReturn(testClub);

        // Act
        ResponseEntity<Club> response = clubController.getClubById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testClub.getId(), response.getBody().getId());
    }

    @Test
    void createClub_ShouldReturnCreatedClub() {
        // Arrange
        when(clubService.createClub(any(Club.class))).thenReturn(testClub);

        // Act
        ResponseEntity<Club> response = clubController.createClub(testClub);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(testClub.getName(), response.getBody().getName());
    }

    @Test
    void updateClub_WhenClubExists_ShouldReturnUpdatedClub() {
        // Arrange
        when(clubService.updateClub(eq(1L), any(Club.class))).thenReturn(testClub);

        // Act
        ResponseEntity<Club> response = clubController.updateClub(1L, testClub);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(testClub.getName(), response.getBody().getName());
    }

    @Test
    void deleteClub_WhenClubExists_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(clubService).deleteClub(1L);

        // Act
        ResponseEntity<Void> response = clubController.deleteClub(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clubService, times(1)).deleteClub(1L);
    }
}
