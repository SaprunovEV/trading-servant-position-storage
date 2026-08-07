package by.sapra.tradingservantpositionstorage.position.application.command;

import by.sapra.tradingservantpositionstorage.position.application.command.utils.PositionFactory;
import by.sapra.tradingservantpositionstorage.position.application.infrastructure.events.cdi.PositionDomainEventPublisher;
import by.sapra.tradingservantpositionstorage.position.domain.aggregate.Position;
import by.sapra.tradingservantpositionstorage.position.domain.command.OpenCommand;
import by.sapra.tradingservantpositionstorage.position.infrostructure.position.jpa.persistons.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandService {
    private final PositionRepository repository;
    private final PositionDomainEventPublisher publisher;

    public void createNewPosition(OpenCommand command) {
        Position position = PositionFactory
                .buildPosition(repository.findAllByPositionId(command.getPositionId()));

        position.append(command);

        publisher.publishAll(position.getDomainEvents());

        repository.saveAll(position.toSave());
    }
}
