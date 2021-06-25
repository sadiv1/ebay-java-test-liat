package com.ebay;

import com.ebay.normalization.javatest.JavaTestApplication;
import com.ebay.normalization.javatest.ReportGenerator;
import com.ebay.normalization.javatest.model.SiteCatalog;
import com.ebay.normalization.javatest.service.CatalogService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes= JavaTestApplication.class)
public class JavaTestApplicationTests {

    @Autowired
    ReportGenerator reportGenerator;

    @Mock
    private CatalogService catalogService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        SiteCatalog siteCatalog = new SiteCatalog();
        List<SiteCatalog> returnCacheValue = new ArrayList<SiteCatalog>( Arrays.asList(siteCatalog) );
        when(catalogService.getCatalogById(1,0)).thenReturn(returnCacheValue);
    }

    @Test
    void contextLoads() {
        reportGenerator.generate();
    }

}
