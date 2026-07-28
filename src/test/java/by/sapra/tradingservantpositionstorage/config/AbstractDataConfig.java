package by.sapra.tradingservantpositionstorage.config;

import by.sapra.tradingservantpositionstorage.testUtils.TestDbFacade;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AbstractDataConfig {
    @Bean
    public TestDbFacade testDbFacade() {
        return new TestDbFacade();
    }
}
