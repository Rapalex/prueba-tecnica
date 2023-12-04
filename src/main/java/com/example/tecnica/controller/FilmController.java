package com.example.tecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tecnica.model.Film;
import com.example.tecnica.service.FilmServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("film")
public class FilmController {

	@Autowired
	private FilmServiceImpl filmServiceImpl;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getFilm(@PathVariable @Min(0) @Max(9) int id) {
		return new ResponseEntity<>(filmServiceImpl.getFilm(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFilm(@PathVariable("filmId") Film film, @Valid @RequestBody Film editedFilm) {

		editedFilm.setId(film.getId());
		return new ResponseEntity<>(filmServiceImpl.updateFilm(editedFilm), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteFilm(@PathVariable("filmId") Film film) {

		return new ResponseEntity<>(filmServiceImpl.deleteFilm(film), HttpStatus.OK);
	}

}
