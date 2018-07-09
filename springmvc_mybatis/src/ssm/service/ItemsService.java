package ssm.service;

import java.util.List;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;


public interface ItemsService {
	//查找全部商品
	/**
	 * 
	 * @param itemsQueryVo 
	 * @return 返回查询的所有的商品的信息
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//查询指定id的商品信息,注意一般要进行参数判断,所以建议使用封装礼物,不然int类型不好判断是否为空
	/**
	 * 
	 * @param id 要查询的商品的id
	 * @throws Exception
	 * @return 返回查询的商品的信息
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	//根据id更新商品信息
	/**
	 * 
	 * @param id 要更新的商品的id
	 * @param itemsCustom 要更新的商品信息
	 * @throws Exception
	 */
	public void updateItemsById(Integer id,ItemsCustom itemsCustom) throws Exception;
	
	//根据id批量删除商品信息
	/**
	 * 
	 * @param id 要更新的商品的id
	 * @param itemsCustom 要更新的商品信息
	 * @throws Exception
	 */
	public void deleteItems(Integer[] itemsIdArr) throws Exception;
	public void deleteItemsBefore(Integer[] itemsIdArr) throws Exception;
}
