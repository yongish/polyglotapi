package com.zhiyong.PolyglotAPI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.zhiyong.PolyglotAPI.db.KnowDAO;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class PolyglotAPIServiceModule extends AbstractModule {
    private final PolyglotAPIConfiguration configuration;
    private final Environment environment;
    private final DBI jdbi;
    private KnowDAO knowDAO;

    public PolyglotAPIServiceModule(PolyglotAPIConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
        DBIFactory factory = new DBIFactory();
        jdbi = factory.build(
                environment,
                configuration.getDataSourceFactory(),
                "mysql"
        );
    }

    @Override
    public void configure() {
        bind(PolyglotAPIConfiguration.class).toInstance(configuration);
        bind(String.class).annotatedWith(Annotations.HealthCheckFormat.class)
                .toInstance(configuration.getFormat());
        bindConstant().annotatedWith(Names.named("name")).to(configuration.getName());
    }

    @Provides @Singleton
    public KnowDAO getKnowDAO() {
        if (knowDAO == null) {
            knowDAO = jdbi.onDemand(KnowDAO.class);
        }
        return knowDAO;
    }
}
