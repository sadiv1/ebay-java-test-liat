package com.ebay;

import com.ebay.normalization.javatest.model.SiteCatalog;
import com.ebay.normalization.javatest.service.CatalogService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CatalogServiceTest {

    @Mock
    private CatalogService catalogService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
     }

    @Test
    public void testCatalogService() {
        SiteCatalog siteCatalog = new SiteCatalog();
        List<SiteCatalog> returnCacheValue = new ArrayList<SiteCatalog>( Arrays.asList(siteCatalog) );
        when(catalogService.getCatalogById(1,0)).thenReturn(returnCacheValue);

        assertEquals(1, returnCacheValue.size());
    }
}
