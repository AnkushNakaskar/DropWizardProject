package com.dropwizard.repository;

import com.dropwizard.entity.BrandEntity;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrandRepository {

    private final List<BrandEntity> brands;

    public BrandRepository(List<BrandEntity> brands) {
        this.brands = ImmutableList.copyOf(brands);
    }

    public List<BrandEntity> findAll(int size) {
        return brands.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    public Optional<BrandEntity> findById(Long id) {
        return brands.stream()
                .filter(brand -> brand.getId().equals(id))
                .findFirst();
    }
}
