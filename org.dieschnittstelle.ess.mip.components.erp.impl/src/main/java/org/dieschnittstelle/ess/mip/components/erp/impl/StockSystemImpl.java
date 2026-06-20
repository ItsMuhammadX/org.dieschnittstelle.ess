package org.dieschnittstelle.ess.mip.components.erp.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.PointOfSaleCRUD;
import org.dieschnittstelle.ess.mip.components.erp.crud.impl.StockItemCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.List;


@ApplicationScoped
@Transactional
@Logged
public class StockSystemImpl implements StockSystem {

    @Inject
    private PointOfSaleCRUD posCRUD;

    @Inject
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
        this.addToStock(product,pointOfScaleId,-units);

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);

        return stockItemCRUD.readStockItemsForPointOfSale(pos)
                .stream()
                .map(StockItem::getProduct)
                .toList();
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        return posCRUD.readAllPointsOfSale()
                .stream()
                .flatMap(pos -> getProductsOnStock(pos.getId()).stream())
                .distinct()
                .toList();
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);
        return stockItem == null ? 0 : stockItem.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        return stockItemCRUD.readStockItemsForProduct(product)
                .stream()
                .mapToInt(StockItem::getUnits)
                .sum();
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        return stockItemCRUD.readStockItemsForProduct(product)
                .stream()
                .map(stockItem -> stockItem.getPos().getId())
                .toList();
    }
}
