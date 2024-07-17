
package com.LiterAlura.LiterAlura.modules;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IDBook(@JsonAlias("title") String bookName,
                     @JsonAlias("languages") List<String> languages) {
}