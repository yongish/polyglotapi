package com.zhiyong.PolyglotAPI.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.zhiyong.PolyglotAPI.Annotations;

public class PolyglotAPIHealthCheck extends HealthCheck {
    private final String format;

    @Inject
    public PolyglotAPIHealthCheck(@Annotations.HealthCheckFormat String format) {
        this.format = format;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
