package com.coinex.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Reeta on 2019-05-22
 */
@Data
public class SnapshotResponse implements Comparable<SnapshotResponse>{

    private String symbol;

    private String symbolCode;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal open;

    private BigDecimal lastTradedPrice;

    private BigDecimal changeRate;

    private BigDecimal changePrice;

    private BigDecimal vol;

    private BigDecimal volValue;

    private BigDecimal close;

    private String baseCurrency;

    private String quoteCurrency;

    private Boolean trading;

    private Integer sort;

    private Integer board;

    private Integer mark;

    private String market;

    private long datetime;

    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapshotResponse that = (SnapshotResponse) o;
        return symbol.equals(that.symbol) && Objects.equals(symbolCode, that.symbolCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, symbolCode);
    }

    @Override
    public int compareTo(SnapshotResponse o) {
        if (this.getSymbol() != null && o.getSymbol() != null) {
            return this.getSymbol().equals(o.getSymbol()) ? 1 : -1;
        }
        return 1;
    }
}
