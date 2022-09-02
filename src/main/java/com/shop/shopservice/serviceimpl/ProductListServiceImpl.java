package com.shop.shopservice.serviceimpl;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shop.shopservice.Idao.IProductListDAO;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.ProductList;
import com.shop.shopservice.service.IProductListService;

@Transactional
@Repository
public class ProductListServiceImpl  implements IProductListService {

	@Autowired
	IProductListDAO productListDao;
	
	@Override
	public List<ProductList> getAllProductList() {
		return productListDao.getAllProductList();
	}

	@Override
	public ProductList getProductListById(int id) {
		return productListDao.getProductListById(id);
	}

	@Override
	public List<ProductList> getProductListByUserId(String userId) {
		return productListDao.getProductListByUserId(userId);
	}

	@Override
	public List<ProductList> getProductListByShopId(String shopId) {
		return productListDao.getProductListByShopId(shopId);
	}

	@Override
	public ProductList getProductList(int id) {
	return productListDao.getProductList(id);
	}

	
	@Override
	public boolean updateProductList(ProductList productList) {
		productListDao.updateProductList(productList);
		return true;
	}

	@Override
	public boolean productListExists(String shopId) {
		productListDao.productListExists(shopId);
		return false;
	}
	
	public boolean createProductList(ProductList productList) {

		if (productListExists(productList.getProductId())) {
			return false;
		} else {
			productListDao.addProductList(productList);			
			return true;
		}
	}

	@Override
	public boolean getProductByProductId(String productId, int cartId) {
		return productListDao.getProductByProductId(productId,cartId);
		 
	}

	@Override
	public ProductList getProductByProductIdAndCartId(String productId, int cartId) {
		return productListDao.getProductByProductIdAndCartId(productId,cartId);
	}

	@Override
	public List<ProductList> getProductListCartId(int cartId) {
		 return productListDao.getProductListCartId(cartId);
		
	}

}
