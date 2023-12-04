package com.example.tecnica.service;

import java.util.Optional;

import com.example.tecnica.model.Film;
import com.example.tecnica.util.ApiResponseDTO;

public interface FilmService {

	Optional<Film> getFilm(int id);

	ApiResponseDTO updateFilm(Film film);

	ApiResponseDTO deleteFilm(Film film);

}
