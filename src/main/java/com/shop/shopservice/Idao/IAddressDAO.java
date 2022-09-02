package com.shop.shopservice.Idao;
import java.util.List;
import com.shop.shopservice.entity.Address;
public interface IAddressDAO {
	
	List<Address> getAllAddress();
	List<Address> getAddressByShopId(String shopId);
	public List<Address> getAllAddressByUserId(String userId);
	public List<Address> getAllAddressByShopId(String shopId);
	public List<Address> getAddressByUserIdAndShopId(String userId, String shopId);
	Address getDefaultAddress(String userId);
	 boolean addressExists(String userId);
	 void addAddress(Address address);
	 Address getAddressById(int id);
	 void updateAddress(Address address);

}
