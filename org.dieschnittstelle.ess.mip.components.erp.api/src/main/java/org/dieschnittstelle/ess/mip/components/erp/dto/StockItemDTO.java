package org.dieschnittstelle.ess.mip.components.erp.dto;

public class StockItemDTO {

    private long posId;
    private long prodId;
    private int units;

    public StockItemDTO(long posId, long prodId, int units) {
        this.posId = posId;
        this.prodId = prodId;
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
