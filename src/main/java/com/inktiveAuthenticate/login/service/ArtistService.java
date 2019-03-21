package com.inktiveAuthenticate.login.service;

import com.inktiveAuthenticate.login.model.Artist;
import com.inktiveAuthenticate.login.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("artistService")
public class ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist findArtistByEmail(String email) {
        return artistRepository.findByEmail(email);
    }

    public Artist findArtistByUsername(String username) {
        return artistRepository.findByUsername(username);
    }

    public Artist findAllArtists() {
        return (Artist) artistRepository.findAll();
    }

}
