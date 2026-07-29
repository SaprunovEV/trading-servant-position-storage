package by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Outbox {
    @Id
    private Long id;
    private String positionId;
}
