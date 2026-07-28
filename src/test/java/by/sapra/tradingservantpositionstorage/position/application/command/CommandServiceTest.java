package by.sapra.tradingservantpositionstorage.position.application.command;

import by.sapra.tradingservantpositionstorage.config.AbstractDataTest;
import by.sapra.tradingservantpositionstorage.testUtils.OpenCommandTestDataBuilder;
import by.sapra.tradingservantpositionstorage.testUtils.TestDbFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = CommandServiceConf.class)
class CommandServiceTest extends AbstractDataTest {
    @Autowired
    CommandService sut;
    @MockitoBean
    BusinessIdCService businessIdCService;

    @Test
    public void createNewPosition() {
        OpenCommandTestDataBuilder aOpenCommand = OpenCommandTestDataBuilder.aOpenCommand();

        String businessId = UUID.randomUUID().toString();
        when(businessIdCService.createNewId())
                .thenReturn(businessId);

        sut.createNewPosition(aOpenCommand.build());


    }
}