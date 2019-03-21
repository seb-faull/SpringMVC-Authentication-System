package com.inktiveAuthenticate.login.repository;

import com.inktiveAuthenticate.login.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("artistRepository")
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByEmail(String email);
    Artist findByUsername(String username);
    List<Artist> findAll();
}
