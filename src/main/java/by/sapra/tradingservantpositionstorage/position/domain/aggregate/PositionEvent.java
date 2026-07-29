package by.sapra.tradingservantpositionstorage.position.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position_event")
public class PositionEvent {
    @Id
    private Long id;
    private String positionId;
}
