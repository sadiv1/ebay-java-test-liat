package com.ebay.normalization.javatest.service;

import com.ebay.normalization.javatest.model.SiteCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebay.normalization.javatest.repository.CatalogRedisRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

    public final static String BASE_URL = "http://localhost:8080/";
    private static final String CATALOG_MAPPING = BASE_URL + "/catalogmapping/getcatalogmapping/json";

    @Autowired
    private CatalogRedisRepository catalogRedisRepository;

    public List<SiteCatalog> getCatalogById(int catalogId, int siteId) {
        logger.info("client requested getCatalogById catalogId: {}, siteId: {}", catalogId, siteId);
        String key = generateKey(catalogId, siteId);
        List<SiteCatalog> cachedCatalog = getCachedCatalog(key);
        if(nonNull(cachedCatalog)) {
            logger.info("cachedCatalog avaliable !!! catalogId: {}, siteId: {}", catalogId, siteId);
            return cachedCatalog;
        }

        logger.info("No cachedCatalog - get data from service key = " + key);
        List<SiteCatalog> catalogs = new ArrayList<SiteCatalog>();
        JSONObject jsonObject = getCatalogMapping(catalogId, siteId);
        JSONArray mappingList = jsonObject.getJSONArray("mappingList");
        for (int j = 0; j < mappingList.length(); j++) {
            JSONObject categoryMapping = mappingList.getJSONObject(j);
            SiteCatalog catalog = new SiteCatalog();
            catalog.setSiteId(siteId);
            catalog.setCatalogId(catalogId);
            catalog.setCategoryName(categoryMapping.getString("categoryName"));
            catalog.setCatalogName(categoryMapping.getString("catalogName"));
            Integer categoryId = categoryMapping.getInt("categoryId");
            catalog.setCategoryId(categoryId);
            catalogs.add(catalog);
        }
        cacheCatalog(key, catalogs);
        return catalogs;
    }

    private JSONObject getCatalogMapping(int catalogId, int siteId) {
        final String url = CATALOG_MAPPING + "/" + catalogId + "/" + siteId;;
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url).asJson();
            logger.info("getCatalogMapping rest call result. key = " + generateKey(catalogId, siteId));
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return response.getBody().getObject();
    }


    private String generateKey(int catalogId, int siteId) {
       return siteId + "_" + catalogId;
    }

    private List<SiteCatalog> getCachedCatalog(String key){
        List<SiteCatalog> catalogs = null;
        try {
            catalogs = catalogRedisRepository.findById(key);
        } catch (Exception e){
            log.warn(format("No catalog was yet cached with the given key : %d!", key));
        }
        return catalogs;
    }

    private void cacheCatalog(String id, List<SiteCatalog> catalogs){
        try{
            catalogRedisRepository.save(id, catalogs);
        }catch (Exception e){
            log.error(format("Unable to store the given catalog : %s!", catalogs));
        }
    }

}
