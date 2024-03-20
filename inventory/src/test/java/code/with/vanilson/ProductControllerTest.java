//package code.with.vanilson;
//
//import code.with.vanilson.controllers.ProductController;
//import code.with.vanilson.dto.ProductDTO;
//import code.with.vanilson.persistence.model.Product;
//import code.with.vanilson.persistence.repository.ProductRepository;
//import code.with.vanilson.services.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = ProductController.class)
//class ProductControllerTest {
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    @MockBean
//    private ProductService productService;
//
//    @InjectMocks
//    private ProductController productController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
//    }
//
//    @Test
//    void shouldReturnAllProduct() throws Exception {
//        List<Product> productIterator = Arrays.asList(
//                new Product("amazon 1", "product on amazon", BigDecimal.valueOf(1200)),
//                new Product("amazon 2", "Clean Code", BigDecimal.valueOf(9900)),
//                new Product("amazon 3", "Design Pattern", BigDecimal.valueOf(8000)),
//                new Product("amazon 4", "MVC Pattern", BigDecimal.valueOf(3200)),
//                new Product("amazon 5", "White Snow", BigDecimal.valueOf(4200))
//        );
//
//        when(productService.getAllProducts()).thenReturn(productIterator);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/v1/product")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        var product = getProductRequest();
//        var productStringRequest = objectMapper.writeValueAsString(product);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/v1/product/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(productStringRequest))
//                .andExpect(status().isCreated());
//
//    }
//
//    private ProductDTO getProductRequest() {
//        return ProductDTO.builder()
//                .name("Iphone 13")
//                .description("latest generation builder xnr 2022")
//                .price(BigDecimal.valueOf(1200))
//                .build();
//    }
//
//}
