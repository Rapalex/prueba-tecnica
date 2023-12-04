package com.example.tecnica.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "films")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

	@Id
	@Min(0)
	@Max(9)
	private int id;

	@JsonProperty("episode_id")
	@NotNull
	private int episodeId;

	@JsonProperty("title")
	@NotNull
	@NotEmpty
	private String title;

	@JsonProperty("release_date")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date releaseDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
