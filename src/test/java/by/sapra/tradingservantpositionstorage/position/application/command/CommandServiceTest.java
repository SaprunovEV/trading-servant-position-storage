package by.sapra.tradingservantpositionstorage.position.application.command;

import by.sapra.tradingservantpositionstorage.config.AbstractDataTest;
import by.sapra.tradingservantpositionstorage.position.domain.aggregate.PositionEvent;
import by.sapra.tradingservantpositionstorage.position.domain.command.OpenCommand;
import by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model.Outbox;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import static by.sapra.tradingservantpositionstorage.testUtils.OpenCommandTestDataBuilder.aOpenCommand;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = CommandServiceConf.class)
class CommandServiceTest extends AbstractDataTest {
    @Autowired
    CommandService sut;

    @Test
    public void createNewPosition() {
        OpenCommand command = aOpenCommand().build();

        sut.createNewPosition(command);

        PositionEvent createEvent = getFacade().findOneByField(PositionEvent.class, "positionId", command.getPositionId());
        Outbox outboxEvent = getFacade().findOneByField(Outbox.class, "positionId", command.getPositionId());


        assertAll(() -> {
            assertNotNull(createEvent, "CreateEvent does not be null");
            assertNotNull(outboxEvent, "OutboxEvent does not be null");
        });
    }
}