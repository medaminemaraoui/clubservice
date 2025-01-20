package org.sid.clubservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sid.clubservice.entity.Club;
import org.sid.clubservice.repository.ClubRepository;
import org.sid.clubservice.service.ClubService;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnotherClubServiceTest {

        @Mock
        private ClubRepository clubRepository;

        @InjectMocks
        private ClubService clubService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetAllClubs() {
            // Arrange
            Club club1 = new Club();
            Club club2 = new Club();
            when(clubRepository.findAll()).thenReturn(Arrays.asList(club1, club2));

            // Act
            List<Club> result = clubService.getAllClubs();

            // Assert
            assertEquals(2, result.size());
            verify(clubRepository, times(1)).findAll();
        }

        @Test
        void testGetClubById() {
            // Arrange
            Club club = new Club();
            club.setId(1L);
            when(clubRepository.findClubById(1L)).thenReturn(club);

            // Act
            Club result = clubService.getClubById(1L);

            // Assert
            assertNotNull(result);
            assertEquals(1L, result.getId());
            verify(clubRepository, times(1)).findClubById(1L);
        }

        @Test
        void testCreateClub() {
            // Arrange
            Club club = new Club();
            when(clubRepository.save(club)).thenReturn(club);

            // Act
            Club result = clubService.createClub(club);

            // Assert
            assertNotNull(result);
            verify(clubRepository, times(1)).save(club);
        }

        @Test
        void testUpdateClub() {
            // Arrange
            Club existingClub = new Club();
            existingClub.setId(1L);
            existingClub.setName("Old Name");

            Club updatedClubDetails = new Club();
            updatedClubDetails.setName("New Name");
            updatedClubDetails.setDescription("New Description");
            updatedClubDetails.setLocation("New Location");
            updatedClubDetails.setCartier("New Cartier");
            updatedClubDetails.setMemberCount(100);

            when(clubRepository.findById(1L)).thenReturn(Optional.of(existingClub));
            when(clubRepository.save(existingClub)).thenReturn(existingClub);

            // Act
            Club result = clubService.updateClub(1L, updatedClubDetails);

            // Assert
            assertEquals("New Name", result.getName());
            assertEquals("New Description", result.getDescription());
            assertEquals("New Location", result.getLocation());
            assertEquals("New Cartier", result.getCartier());
            assertEquals(100, result.getMemberCount());
            verify(clubRepository, times(1)).findById(1L);
            verify(clubRepository, times(1)).save(existingClub);
        }

        @Test
        void testDeleteClub() {
            // Arrange
            doNothing().when(clubRepository).deleteById(1L);

            // Act
            clubService.deleteClub(1L);

            // Assert
            verify(clubRepository, times(1)).deleteById(1L);
        }
    }

