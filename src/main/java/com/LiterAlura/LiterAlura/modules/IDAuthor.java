package com.LiterAlura.LiterAlura.modules;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IDAuthor(@JsonAlias("name") String authorName,
                       @JsonAlias("birth_year") Integer birthYear,
                       @JsonAlias("death_year") Integer deathYear) {
}