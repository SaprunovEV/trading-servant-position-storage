package by.sapra.tradingservantpositionstorage.position.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PositionEvent {
    @Id
    private Long id;
    private String positionId;
}
