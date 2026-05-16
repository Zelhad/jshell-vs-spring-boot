package  com.vendti.app.TestRunner;

import common.model.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {

        Product product = new Product(
                1L,
                "desc",
                "name from TestRunner class",
                "http://api.example",
                false
        );

        String ref = product.getClass().getName()
                + "@"
                + Integer.toHexString(System.identityHashCode(product));

        System.out.println("Memory Product reference: " + ref);

        log.info("Product object: {}", product);
    }
}