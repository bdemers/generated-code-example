package com.okta.example.server;

import com.codahale.metrics.health.HealthCheck;
import com.okta.example.server.dao.DefaultStormtrooperDao;
import com.okta.example.server.dao.DefaultTieCraftDao;
import com.okta.example.server.dao.StormtrooperDao;
import com.okta.example.server.dao.TieCraftDao;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
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
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        // look up config yaml on the classpath
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());

        // expose swagger
        bootstrap.addBundle(new SwaggerBundle<ExampleConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ExampleConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
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

        // use @Inject to bind the DAOs
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new DefaultStormtrooperDao()).to(StormtrooperDao.class);
                bind(new DefaultTieCraftDao()).to(TieCraftDao.class);
            }
        });
    }
}