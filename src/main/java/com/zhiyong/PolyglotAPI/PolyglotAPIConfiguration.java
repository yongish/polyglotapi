package com.zhiyong.PolyglotAPI;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class PolyglotAPIConfiguration extends Configuration {
    private static final String DATABASE = "database";

    @NotEmpty @JsonProperty
    private String format;

    @Valid @NotNull @JsonProperty(DATABASE)
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty(DATABASE)
    DataSourceFactory getDataSourceFactory() {
        return database;
    }

    void setDataSourceFactory(final DataSourceFactory factory) {
        database = factory;
    }

    @JsonProperty("name")
    private String name;

//    @NotNull
//    @JsonProperty("jooq")
//    private JooqFactory jooqFactory;
//
//    @JsonProperty("jooq")
//    JooqFactory getJooqFactory() {
//        jooqFactory.setDialect(Optional.of(SQLDialect.MYSQL));
//        return jooqFactory;
//    }
}
