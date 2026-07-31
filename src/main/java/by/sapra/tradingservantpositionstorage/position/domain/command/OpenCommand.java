package by.sapra.tradingservantpositionstorage.position.domain.command;

import by.sapra.tradingservantpositionstorage.position.domain.aggregate.PositionEvent;
import lombok.AllArgsConstructor;import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenCommand {
    private BigDecimal entryPrise;
    private BigDecimal maxIo;
    private String type;
    private Instant openDate;
    private Integer leverage;
    private String targetCoin;
    private String pnlCoin;

    public static PositionEvent toEvent() {
        return null;
    }
}

