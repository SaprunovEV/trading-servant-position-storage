package by.sapra.tradingservantpositionstorage.position.application.command;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CommandServiceConf {
    @Bean
    public CommandService sut() {
        return new CommandService();
    }
}
