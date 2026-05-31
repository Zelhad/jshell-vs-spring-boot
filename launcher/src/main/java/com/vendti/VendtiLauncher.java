package com.vendti;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class VendtiLauncher {

    public static void main(String[] args) {
        SpringApplication.run(VendtiLauncher.class, args);
}
}
