package com.example.tecnica.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.tecnica.model.Film;
import com.example.tecnica.service.FilmServiceImpl;
import com.example.tecnica.util.ApiResponseDTO;

@ExtendWith(MockitoExtension.class)
class FilmControllerTest {
	
	@InjectMocks
	FilmController filmController;

    @Mock
    FilmServiceImpl filmServiceImpl;

    @Test
    public void testGetFilm()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(filmServiceImpl.getFilm(any(int.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = filmController.getFilm(1);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void testUpdateFilm()
    {
    	Film film = new Film();
    	film.setId(1);
    	
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(filmServiceImpl.updateFilm(any(Film.class))).thenReturn(new ApiResponseDTO("Film actualizado correctamente", "OK_001"));

        ResponseEntity<Object> responseEntity = filmController.updateFilm(film, film);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteFilm()
    {
    	Film film = new Film();
    	film.setId(1);
    	
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(filmServiceImpl.deleteFilm(any(Film.class))).thenReturn(new ApiResponseDTO("Film eliminado correctamente", "OK_002"));

        ResponseEntity<Object> responseEntity = filmController.deleteFilm(film);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
