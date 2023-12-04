package com.example.tecnica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tecnica.model.Film;
import com.example.tecnica.repository.FilmRepository;
import com.example.tecnica.util.ApiResponseDTO;

@Service
public class FilmServiceImpl implements FilmService {
	
	private final RestTemplate restTemplate;

	@Autowired
	FilmRepository filmRepository;

	@Value("${starwarsapi.filmApi}")
	private String endpoint;
	
	public FilmServiceImpl(RestTemplateBuilder restTemplateBuilder,
            @Value("${starwarsapi.baseUrl}") String baseUrl) {
		this.restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
	}

	@Override
	public Optional<Film> getFilm(int id) {

		ResponseEntity<Film> response;
		try {
			response = restTemplate.getForEntity(this.endpoint, Film.class, id);
		} catch (Exception e) {
			throw e;
		}

		Optional<Film> film = Optional.ofNullable(response.getBody());

		film.get().setId(id);
		filmRepository.saveAndFlush(film.get());

		return film;
	}

	@Override
	public ApiResponseDTO updateFilm(Film film) {
		try {
			filmRepository.saveAndFlush(film);
			return new ApiResponseDTO("Film actualizado correctamente", "OK_001");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ApiResponseDTO deleteFilm(Film film) {
		try {
			filmRepository.delete(film);
			return new ApiResponseDTO("Film eliminado correctamente", "OK_002");
		} catch (Exception e) {
			throw e;
		}
	}

}
