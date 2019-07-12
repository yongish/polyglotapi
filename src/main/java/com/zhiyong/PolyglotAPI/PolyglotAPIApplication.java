package com.zhiyong.PolyglotAPI;

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
        // TODO: application initialization
    }

    @Override
    public void run(final PolyglotAPIConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
