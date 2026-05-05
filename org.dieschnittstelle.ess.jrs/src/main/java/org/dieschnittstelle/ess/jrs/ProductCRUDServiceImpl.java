package org.dieschnittstelle.ess.jrs;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import java.util.List;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 *
 * ProductCRUD
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDServiceImpl.class);


	private GenericCRUDExecutor<AbstractProduct> productCRUD;


	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		logger.info("<constructor>: " + servletContext + "/" + request);
		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
		logger.debug("read out the productCRUD from the servlet context: " + this.productCRUD);
	}


	@Override
	public AbstractProduct createProduct(AbstractProduct prod) {
		// TODO Auto-generated method stub
		return this.productCRUD.createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		// TODO Auto-generated method stub
		return this.productCRUD.readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id,
	                                     AbstractProduct update) {
		// TODO Auto-generated method stub
		update.setId(id);
		return this.productCRUD.updateObject(update);

	}

	@Override
	public boolean deleteProduct(long id) {
		// TODO Auto-generated method stub
		return this.productCRUD.deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		// TODO Auto-generated method stub
		AbstractProduct item = this.productCRUD.readObject(id);

		if(item != null){
			return item;
		}
		else{
			throw new NotFoundException("The productItem with id " + id + " does not exist!");
		}
	}
	
}
