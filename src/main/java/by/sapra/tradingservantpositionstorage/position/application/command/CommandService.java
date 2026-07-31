package by.sapra.tradingservantpositionstorage.position.application.command;

import by.sapra.tradingservantpositionstorage.position.application.command.utils.PositionFactory;
import by.sapra.tradingservantpositionstorage.position.domain.aggregate.Position;
import by.sapra.tradingservantpositionstorage.position.domain.command.OpenCommand;
import by.sapra.tradingservantpositionstorage.position.infrostructure.position.jpa.persistons.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final BusinessIdCService idCService;
    private final PositionRepository repository;

    public void createNewPosition(OpenCommand build) {
        String position_id = idCService.createNewId();

        Position position = PositionFactory.buildPosition(repository.findAllByPositionId(position_id));

        repository.save(OpenCommand.toEvent());
    }
}
