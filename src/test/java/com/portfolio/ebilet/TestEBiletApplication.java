package com.portfolio.ebilet;

import org.springframework.boot.SpringApplication;

public class TestEBiletApplication {

    public static void main(String[] args) {
        SpringApplication.from(eBiletApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
