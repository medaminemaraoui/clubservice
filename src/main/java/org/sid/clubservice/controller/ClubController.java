package org.sid.clubservice.controller;


import org.sid.clubservice.entity.Club;
import org.sid.clubservice.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

//    @GetMapping
//    public List<Club> getAllClubs() {
//        return clubService.getAllClubs();
//    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs =  clubService.getAllClubs();
        return ResponseEntity.ok(clubs); // Wrap the list in a ResponseEntity
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club clubs =  clubService.getClubById(id);
        return ResponseEntity.ok(clubs);
    }

//    @PostMapping
//    public Club createClub(@RequestBody Club club) {
//        return clubService.createClub(club);
//    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club clubs = clubService.createClub(club);
        return new ResponseEntity<>(clubs, HttpStatus.CREATED); // Return CREATED status
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club clubDetails) {
        return ResponseEntity.ok(clubService.updateClub(id, clubDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
