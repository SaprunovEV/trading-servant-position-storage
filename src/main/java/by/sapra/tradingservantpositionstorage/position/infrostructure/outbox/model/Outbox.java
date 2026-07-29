package by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outbox")
public class Outbox {
    @Id
    private Long id;
    private String positionId;
}
