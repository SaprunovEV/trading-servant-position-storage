package by.sapra.tradingservantpositionstorage.testUtils;

import by.sapra.tradingservantpositionstorage.position.domain.command.OpenCommand;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class OpenCommandTestDataBuilder implements TestDataBuilder<OpenCommand>{
    private String positionId = UUID.randomUUID().toString();
    private BigDecimal entryPrise = BigDecimal.valueOf(.345);
    private BigDecimal maxIo = BigDecimal.valueOf(23.3456);
    private String type = "Cross Short";
    private Instant openDate = Instant.now();
    private Integer leverage = 10;
    private String targetCoin = "BTC";
    private String pnlCoin = "USDT";

    private OpenCommandTestDataBuilder() {}

    public static OpenCommandTestDataBuilder aOpenCommand() {
        return new OpenCommandTestDataBuilder();
    }

    @Override
    public OpenCommand build() {
        return OpenCommand.builder()
                .positionId(positionId)
                .type(type)
                .maxIo(maxIo)
                .openDate(openDate)
                .pnlCoin(pnlCoin)
                .targetCoin(targetCoin)
                .entryPrise(entryPrise)
                .leverage(leverage)
                .build();
    }
}
