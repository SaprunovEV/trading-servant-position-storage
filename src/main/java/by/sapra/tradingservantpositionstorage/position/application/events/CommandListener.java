package by.sapra.tradingservantpositionstorage.position.application.events;

import by.sapra.tradingservantpositionstorage.position.domain.external.event.OpenDomainEvent;
import by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.jpa.OutboxRepository;
import by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model.Outbox;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandListener {
    private final OutboxRepository repository;

    @EventListener
    public void handleOpenCommand(OpenDomainEvent event) {

        repository.save(Outbox.builder().build());
    }
}
