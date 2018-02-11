package com.okta.example.server;

import com.codahale.metrics.health.HealthCheck;
import com.okta.example.server.dao.DefaultStormtrooperDao;
import com.okta.example.server.dao.StormtrooperDao;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


public class ExampleApplication extends Application<ExampleConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "server-example";
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) {

        // health check
        environment.healthChecks().register("example", new HealthCheck() {
            @Override
            protected Result check() {
                // Everything is in memory, so we are always healthy ;)
                return Result.healthy();
            }
        });

        // Load any resource in the resources package
        environment.jersey().packages(getClass().getPackage().getName() + ".resources");

        // use @Inject to bind the StormtrooperDao
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(stormtrooperDao()).to(StormtrooperDao.class);
            }
        });
    }

    private StormtrooperDao stormtrooperDao() {
        return new DefaultStormtrooperDao();
    }
}