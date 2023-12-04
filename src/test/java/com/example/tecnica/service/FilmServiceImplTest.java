package com.example.tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.tecnica.model.Film;
import com.example.tecnica.repository.FilmRepository;
import com.example.tecnica.util.ApiResponseDTO;

@RestClientTest({FilmServiceImpl.class})

class FilmServiceImplTest {

	@Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private FilmServiceImpl filmServiceImpl;
    
    @MockBean
    private FilmRepository filmRepository;

    @AfterEach
    void resetMockServer() {
        mockRestServiceServer.reset();
        
    }

    @Test
    void getFilm_sucess() {

        final var filmId = 1;
        final var filmJson = new ClassPathResource("new_hope.json");

        mockRestServiceServer
                .expect(requestTo("/films/" + filmId))
                .andRespond(withSuccess(filmJson, MediaType.APPLICATION_JSON));

        var character = filmServiceImpl.getFilm(filmId);

        assertTrue(character.isPresent());
        assertEquals(4, character.get().getEpisodeId());
        assertEquals("A New Hope", character.get().getTitle());
    }

    @Test
    void getFilm_notFound() {

        final var idUnknownCharacter = 40;

        mockRestServiceServer
                .expect(requestTo("/films/" + idUnknownCharacter))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(NotFound.class, () -> {
        	filmServiceImpl.getFilm(idUnknownCharacter);
		});
    }
    
    @Test
    void updateFilm_sucess() {
    	Film film = new Film();
    	
    	given(filmRepository.saveAndFlush(film))
        .willReturn(film);
    	
    	ApiResponseDTO apiResponseDto = filmServiceImpl.updateFilm(film);
    	
    	assertEquals("OK_001", apiResponseDto.getResponseCode());
    }
    
    @Test
    void updateFilm_error() {
    	Film film = new Film();
    	
    	given(filmRepository.saveAndFlush(film)).willThrow(new RuntimeException());
    	
    	assertThrows(Exception.class, () -> {
    		filmServiceImpl.updateFilm(film);
		});
    }
    
    @Test
    void deleteFilm_sucess() {
    	Film film = new Film();
    	
    	willDoNothing().given(filmRepository).delete(film);
    	
    	filmServiceImpl.deleteFilm(film);
    	
    	verify(filmRepository, times(1)).delete(film);
    }

}
