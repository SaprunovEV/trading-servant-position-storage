package by.sapra.tradingservantpositionstorage.position.application.command;

import by.sapra.tradingservantpositionstorage.position.infrostructure.position.jpa.persistons.PositionRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CommandServiceConf {
    @Bean
    public CommandService sut(BusinessIdCService idSer, PositionRepository repo) {
        return new CommandService(idSer, repo);
    }
}
