package com.ebay.normalization.javatest.repository;

import com.ebay.normalization.javatest.model.SiteCatalog;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CatalogRedisRepositoryImpl implements CatalogRedisRepository{

    public static final String HASH_KEY = "Catalog";

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void save(String key, List<SiteCatalog> catalogs){
        RMap<String, List<SiteCatalog>> map = redissonClient.getMap(HASH_KEY);
        map.put(key, catalogs);
    }

    @Override
    public List<SiteCatalog> findById(String key) {
        RMap<String, List<SiteCatalog>> map = redissonClient.getMap(HASH_KEY);
        return map.get(key);
    }

}