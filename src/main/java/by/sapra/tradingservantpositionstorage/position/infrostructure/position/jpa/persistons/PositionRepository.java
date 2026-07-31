package by.sapra.tradingservantpositionstorage.position.infrostructure.position.jpa.persistons;

import by.sapra.tradingservantpositionstorage.position.domain.aggregate.PositionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEvent, Long> {
    List<PositionEvent> findAllByPositionId(String positionId);
}
