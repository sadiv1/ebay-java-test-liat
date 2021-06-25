package com.ebay;

import com.ebay.normalization.javatest.JavaTestApplication;
import com.ebay.normalization.javatest.model.SiteCatalog;
import com.ebay.normalization.javatest.repository.CatalogRedisRepository;
import com.ebay.normalization.javatest.service.CatalogService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes= JavaTestApplication.class)
public class CatalogRedisRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CatalogService catalogService;

    @Mock
    CatalogRedisRepository repository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findById_basic() {
        List<SiteCatalog> list = repository.findById("0_1");
        assertEquals("JPA in 50 Steps", list);
    }

    @org.junit.Test
    public void usingMockito() {
//        Optional<List<SiteCatalog>> res =
////        List<String> allTodos = Arrays.asList("Learn Spring MVC",
////                "Learn Spring", "Learn to Dance");
//        int catalogId = 1;
//        int siteId = 0;
//        when(catalogService.getCatalogById(catalogId,siteId).thenReturn(res);
//        CatalogServiceImpl catalogServiceImpl = new CatalogServiceImpl(catalogService);
////        List<String> todos = catalogServiceImpl
////                .retrieveTodosRelatedToSpring("Ranga");
////        Assert.assertEquals(2, todos.size());
    }

}
