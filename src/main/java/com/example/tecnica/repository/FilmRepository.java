package com.example.tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tecnica.model.Film;

@Repository("filmRepository")
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
