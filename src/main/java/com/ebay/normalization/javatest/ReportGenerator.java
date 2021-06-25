package com.ebay.normalization.javatest;


import com.ebay.normalization.javatest.service.ReportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReportGenerator {

    Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

    @Autowired
    ReportServiceImpl reportService;

    @EventListener(ApplicationReadyEvent.class)
    public void generate() {

        logger.info("Starting generate Report");
        reportService.generateReport();
        logger.info("Report Done");


//        List<Integer> siteIds = Arrays.asList(0); //, 2, 3, 77);
//        for (Integer siteId : siteIds) {
//            for (int catalogId = 1; catalogId < 201; catalogId++) {
//                Optional<Catalog> catalog = service.getCatalogById(catalogId, siteId);
//                //String url = "http://localhost:8080/catalogmapping/getcatalogmapping/json/" + catalogId + "/" + siteId;
//                //HttpResponse<String> httpResponse = Unirest.get(url).asString();
//                //JSONObject jsonObject = new JSONObject(httpResponse.getBody());
//                //JSONArray mappingList = jsonObject.getJSONArray("mappingList");
//                //for (int j = 0; j < mappingList.length(); j++) {
//                //    JSONObject categoryMapping = mappingList.getJSONObject(j);
//                //    System.out.println("Site " + siteId + " - Catalog " + catalogId + " is mapped to category " + "\"" + categoryMapping.getString("categoryName") + "\" (" + categoryMapping.getInt("categoryId") + ")");
//                }
//            }
//        }
       System.out.println("===========");
        System.out.println("Report Done");
        System.out.println("===========");
    }

}
