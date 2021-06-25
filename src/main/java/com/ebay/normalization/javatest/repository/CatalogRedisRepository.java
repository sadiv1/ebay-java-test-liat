package com.ebay.normalization.javatest.repository;

import com.ebay.normalization.javatest.model.SiteCatalog;

import java.util.List;


public interface CatalogRedisRepository {
    // Save a new catalog.
    void save(String key, List<SiteCatalog> catalogs);

    // Find catalog by key.
    List<SiteCatalog> findById(String key);
}
