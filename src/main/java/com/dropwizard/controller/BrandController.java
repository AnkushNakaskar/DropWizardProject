package com.dropwizard.controller;


import com.dropwizard.entity.BrandEntity;
import com.dropwizard.repository.BrandRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandController {
    private final int defaultSize;
    private final BrandRepository brandRepository;

    public BrandController(int defaultSize, BrandRepository brandRepository) {
        this.defaultSize = defaultSize;
        this.brandRepository = brandRepository;
    }

    @GET
    public List<BrandEntity> getBrands(@QueryParam("size") Optional<Integer> size) {
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    public BrandEntity getById(@PathParam("id") Long id) {
        return brandRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
