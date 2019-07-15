package com.zhiyong.PolyglotAPI.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class Word {
    @NotEmpty
    @JsonProperty("word")
    private final String word;

    @NotEmpty
    @JsonProperty("pinyin")
    private final String pinyin;

    @NotNull
    @JsonProperty("know")
    private final int know;

    @NotEmpty
    @JsonProperty("url")
    private final String url;

    @NotEmpty
    @JsonProperty("title")
    private final String title;
}
