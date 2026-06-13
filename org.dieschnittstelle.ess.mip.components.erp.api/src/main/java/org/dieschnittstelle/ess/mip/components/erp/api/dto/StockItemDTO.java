package org.dieschnittstelle.ess.mip.components.erp.api.dto;

public class StockItemDTO {

    private long posId;
    private long prodId;
    private int units;

    public StockItemDTO() {
    }

    public StockItemDTO(long prodId, long posId, int units) {
        this.prodId = prodId;
        this.posId = posId;
        this.units = units;
    }

    public long getPosId() {
        return posId;
    }

    public long getProdId() {
        return prodId;
    }

    public int getUnits() {
        return units;
    }

    public void setPosId(long posId) {
        this.posId = posId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
