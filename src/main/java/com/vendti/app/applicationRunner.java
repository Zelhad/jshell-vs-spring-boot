package com.vendti.app;
import common.model.product.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(scanBasePackageClasses = Product.class)
@SpringBootApplication
public class applicationRunner {

    public static void main(String[] args) {

        SpringApplication.run(applicationRunner.class, args);

        System.out.println("Spring Boot application started...");
    }
}



        /*
        package your.package.name;

import common.model.product.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = Product.class)
public class ApplicationRunner {

    public static void main(String[] args) {

        SpringApplication.run(ApplicationRunner.class, args);

        System.out.println("Spring Boot application started...");
    }
}




        Product product = new Product(
                1L,
                "name",
                "desc",
                "http://api.exampl",
                false
        );
//        System.out.println(product);
/*


        Product product = new Product(
                1L,
                "name",
                "desc",
                "http://api.exampl",
                false
        );
        System.out.println(product);
        int i = product.hashCode();
        //System.out.println(i);
        Long id = product.getId();
        String string = product.toString();
        System.out.println(string);
        System.out.println("Hello product from springboot && the id of the product is " + id);

    }

 */


//}
