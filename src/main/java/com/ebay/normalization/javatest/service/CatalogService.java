package com.ebay.normalization.javatest.service;

import com.ebay.normalization.javatest.model.SiteCatalog;
import java.util.List;

public interface CatalogService {
    List<SiteCatalog> getCatalogById(int catalogId, int siteId);
}
