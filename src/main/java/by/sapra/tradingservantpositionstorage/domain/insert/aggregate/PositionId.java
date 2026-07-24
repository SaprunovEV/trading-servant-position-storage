package by.sapra.tradingservantpositionstorage.domain.insert.aggregate;


import by.sapra.tradingservantpositionstorage.domain.insert.entityObjects.CoinPair;
import by.sapra.tradingservantpositionstorage.domain.insert.events.DataEvent;
import by.sapra.tradingservantpositionstorage.domain.insert.valueObjects.PositionState;
import by.sapra.tradingservantpositionstorage.domain.insert.valueObjects.PositionStatus;

import java.util.List;

public class PositionId {
    private String Id;
    private PositionState state;
    private CoinPair pair;
    private PositionStatus status;

    private List<DataEvent> events;
}
