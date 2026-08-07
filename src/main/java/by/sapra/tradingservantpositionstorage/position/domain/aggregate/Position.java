package by.sapra.tradingservantpositionstorage.position.domain.aggregate;

import by.sapra.tradingservantpositionstorage.position.domain.command.OpenCommand;
import by.sapra.tradingservantpositionstorage.position.domain.external.event.PositionDomainEvent;

import java.util.List;

public class Position {

    public PositionDomainEvent getDomainEvents() {
        return null;
    }

    public void append(OpenCommand command) {

    }

    public List<PositionEvent> toSave() {
        return null;
    }
}
