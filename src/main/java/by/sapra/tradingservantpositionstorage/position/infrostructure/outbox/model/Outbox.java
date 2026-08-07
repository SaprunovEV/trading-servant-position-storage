package by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "outbox")
public class Outbox {
    @Id
    private Long id;
    private String positionId;

    private String payload;
    private String topic;
    private String group;

    private OutType outType;
}
