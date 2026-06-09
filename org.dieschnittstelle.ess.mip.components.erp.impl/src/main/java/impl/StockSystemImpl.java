package impl;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.PointOfSaleCRUD;
import org.dieschnittstelle.ess.mip.components.erp.crud.impl.StockItemCRUD;

import java.util.List;


public class StockSystemImpl implements StockSystem {

    private PointOfSaleCRUD posCRUD;

    private StockItemCRUD stockItemCRUD;


    public void addToStock(IndividualisedProductItem product, long pointOfScaleId, int units){
        PointOfSale pos = posCRUD.readPointOfSale(pointOfScaleId);
        StockItem si = stockItemCRUD.readStockItem(product,pos);
        if(si == null){
            si = new StockItem(product, pos, units);
            stockItemCRUD.createStockItem(si);

        }else {
            si.setUnits(si.getUnits() + units);
            stockItemCRUD.updateStockItem(si);
        }
    }

    public void removeFromStock(IndividualisedProductItem product, long pointOfScaleId, int units){

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        return List.of();
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        return List.of();
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        return 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        return 0;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        return List.of();
    }
}
