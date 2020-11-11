package com.dropwizard.controller;


import com.dropwizard.entity.BrandEntity;
import com.dropwizard.repository.BrandRepository;
import io.dropwizard.discovery.client.DiscoveryClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandController {
    private final int defaultSize;
    private final BrandRepository brandRepository;
    private final DiscoveryClient client;

    public BrandController(int defaultSize, BrandRepository brandRepository,DiscoveryClient client) {
        this.defaultSize = defaultSize;
        this.brandRepository = brandRepository;
        this.client=client;
    }

    @GET
    public List<BrandEntity> getBrands(@QueryParam("size") Optional<Integer> size) {
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    public BrandEntity getById(@PathParam("id") Long id) throws Exception {
        String name = client.getInstance().getName();
        System.out.println("Name>>>>"+name);
        System.out.println("Port>>>>"+client.getInstance().getPort());
        return brandRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
