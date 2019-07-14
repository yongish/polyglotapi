package com.zhiyong.PolyglotAPI.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class Article {
    @JsonProperty("title")
    private final String title;

    @NotEmpty
    @JsonProperty("url")
    private final String url;

    @NotEmpty
    @JsonProperty("word")
    private final String word;

    @NotEmpty
    @JsonProperty("language")
    private final String language;

    @NotNull
    @JsonProperty("know")
    private final int know;
}
