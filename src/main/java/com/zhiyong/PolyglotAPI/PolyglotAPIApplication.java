package com.zhiyong.PolyglotAPI;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zhiyong.PolyglotAPI.health.PolyglotAPIHealthCheck;
import com.zhiyong.PolyglotAPI.resources.KnowResource;
import com.zhiyong.PolyglotAPI.resources.WordResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PolyglotAPIApplication extends Application<PolyglotAPIConfiguration> {
    public static void main(final String[] args) throws Exception {
        new PolyglotAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "PolyglotAPI";
    }

    @Override
    public void initialize(final Bootstrap<PolyglotAPIConfiguration> bootstrap) {
    }

    @Override
    public void run(final PolyglotAPIConfiguration configuration,
                    final Environment environment) {
        Injector injector = Guice.createInjector(new PolyglotAPIServiceModule(configuration, environment));

        environment.healthChecks().register("PolyglotAPI", injector.getInstance(PolyglotAPIHealthCheck.class));

        environment.jersey().register(injector.getInstance(KnowResource.class));
        environment.jersey().register(injector.getInstance(WordResource.class));
    }

}
