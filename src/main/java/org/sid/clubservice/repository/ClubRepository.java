package org.sid.clubservice.repository;


import org.sid.clubservice.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Club findByName(String name);

    Club findClubById(Long id);
}
