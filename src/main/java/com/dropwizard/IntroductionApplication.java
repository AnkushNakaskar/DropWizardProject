package com.dropwizard;

import com.dropwizard.config.ProjectConfig;
import com.dropwizard.controller.BrandController;
import com.dropwizard.entity.BrandEntity;
import com.dropwizard.repository.BrandRepository;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.discovery.DiscoveryBundle;
import io.dropwizard.discovery.DiscoveryFactory;
import io.dropwizard.discovery.client.DiscoveryClient;
import io.dropwizard.discovery.client.DiscoveryClientManager;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Arrays;
import java.util.List;

public class IntroductionApplication extends Application<ProjectConfig> {


    private final DiscoveryBundle<ProjectConfig> discoveryBundle = new DiscoveryBundle<ProjectConfig>() {
        @Override
        public DiscoveryFactory getDiscoveryFactory(ProjectConfig configuration) {
            return configuration.getDiscoveryFactory();
        }

    };

    public static void main(String[] args) throws Exception {
        new IntroductionApplication().run("server","application.yml");
    }

    @Override
    public void run(ProjectConfig configuration, Environment environment) throws Exception {
        int defaultSize = configuration.getDefaultSize();
        BrandRepository brandRepository = new BrandRepository(initBrands());

        final DiscoveryClient client = discoveryBundle.newDiscoveryClient("other-service");
        environment.lifecycle().manage(new DiscoveryClientManager<>(client));
        BrandController brandResource = new BrandController(defaultSize, brandRepository,client);
        environment
                .jersey()
                .register(brandResource);
    }

    private List<BrandEntity> initBrands() {
        BrandEntity entity =new BrandEntity();
        entity.setId(1L);
        entity.setName("Name");
        return Arrays.asList(entity);
    }

    @Override
    public void initialize(Bootstrap<ProjectConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.addCommand(new WaitingCmd(this));
        bootstrap.addBundle(discoveryBundle);
        super.initialize(bootstrap);
    }
}
