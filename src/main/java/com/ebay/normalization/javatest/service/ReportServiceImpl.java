package com.ebay.normalization.javatest.service;

import com.ebay.normalization.javatest.model.SiteCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:catalog.properties")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CatalogService catalogService;

    @Value( "${catalogIdStart}" )
    private Integer catalogIdStart;

    @Value( "${catalogIdEnd}" )
    private Integer catalogIdEnd;

    @Value("#{'${siteIds}'.split(',')}")
    private List<Integer> siteIds;

    @Override
    public void generateReport() {
        Set<Integer> allCategories = new HashSet<Integer>();
        for (Integer siteId : siteIds) {
            for (int catalogId = catalogIdStart; catalogId < catalogIdEnd; catalogId++) {
                List<SiteCatalog> catalogs = catalogService.getCatalogById(catalogId, siteId);
                Set<Integer> categories = catalogs.stream()
                                            .map(SiteCatalog::getCategoryId)
                                            .collect(Collectors.toSet());
                allCategories.addAll(categories);
                printCatalogReport(catalogs);
            }
            printTotalCatalogsInfo(allCategories);
        }
    }

    private void printCatalogReport(List<SiteCatalog> catalogs) {
        Collections.sort(catalogs);
        catalogs.stream().forEach((c) -> printCatalog(c));
    }

    private void printCatalog(SiteCatalog catalog) {
        System.out.println((new StringBuilder())
                .append("Site ").append(catalog.getSiteId())
                .append(" - Catalog ").append(catalog.getCatalogId())
                .append(" is mapped to category ").append(catalog.getCategoryName())
                .append("(").append(catalog.getCategoryId()).append(")")
                .toString());
    }

    private void printTotalCatalogsInfo(Set<Integer> allCategories) {
        System.out.println((new StringBuilder())
                .append("Total Unique categories: ").append(allCategories.size())
                .toString());
    }
}
