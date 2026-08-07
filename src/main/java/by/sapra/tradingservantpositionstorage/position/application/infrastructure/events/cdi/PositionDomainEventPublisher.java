package by.sapra.tradingservantpositionstorage.position.application.infrastructure.events.cdi;

import by.sapra.tradingservantpositionstorage.position.domain.external.event.PositionDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PositionDomainEventPublisher {
    private final ApplicationEventPublisher publisher;
    public void publishAll(PositionDomainEvent domainEvents){
        publisher.publishEvent(domainEvents);
    }
}
