package by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.jpa;

import by.sapra.tradingservantpositionstorage.position.infrostructure.outbox.model.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
