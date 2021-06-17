package com.ebay.normalization.javatest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ReportGenerator {

    @EventListener(ApplicationReadyEvent.class)
    public void generate() throws UnirestException {
        List<Integer> siteIds = Arrays.asList(0, 2, 3, 77);
        for (Integer siteId : siteIds) {
            for (int i = 1; i < 201; i++) {
                String url = "http://localhost:8080/catalogmapping/getcatalogmapping/json/" + i + "/" + siteId;
                HttpResponse<String> httpResponse = Unirest.get(url).asString();
                JSONObject jsonObject = new JSONObject(httpResponse.getBody());
                JSONArray mappingList = jsonObject.getJSONArray("mappingList");
                for (int j = 0; j < mappingList.length(); j++) {
                    JSONObject categoryMapping = mappingList.getJSONObject(j);
                    System.out.println("Site " + siteId + " - Catalog " + i + " is mapped to category " + "\"" + categoryMapping.getString("categoryName") + "\" (" + categoryMapping.getInt("categoryId") + ")");
                }
            }
        }
        System.out.println("===========");
        System.out.println("Report Done");
        System.out.println("===========");
    }

}
