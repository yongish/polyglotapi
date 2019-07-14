package com.zhiyong.PolyglotAPI.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class Know {
    @NotEmpty
    @JsonProperty("word")
    private final String word;

    @JsonProperty("know")
    private final int know;
}
