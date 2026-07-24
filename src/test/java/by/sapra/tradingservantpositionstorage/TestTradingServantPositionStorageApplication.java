package by.sapra.tradingservantpositionstorage;

import org.springframework.boot.SpringApplication;

public class TestTradingServantPositionStorageApplication {

    public static void main(String[] args) {
        SpringApplication.from(TradingServantPositionStorageApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
