package com.ebay.normalization.javatest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Catalog")
public class SiteCatalog implements Comparable<SiteCatalog>, Serializable {
    @Id
    private String id;

    private int siteId;

    private int categoryId;

    private int catalogId;
    private String categoryName;
    private String catalogName;

    @Override
    public int compareTo(SiteCatalog c) {
        return this.getCategoryId() - c.getCategoryId();
    }
}