package ssm.mapper;

import java.util.List;

import ssm.po.Items;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

public interface ItemsMapper {
	/**
	 * 
	 * @return List<ItemsCustom> 返回所有的商品的信息
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	/**
	 * 
	 * @param id
	 *            要查询的商品的id
	 * @return Items 返回指定id的商品的信息
	 * @throws Exception
	 */
	public Items findItemsById(int id) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的商品的id
	 * @param itemsCustom
	 *            要修改的商品的pojo包装
	 * @throws Exception
	 */
	public void updateItemsById(int id, ItemsCustom itemsCustom) throws Exception;
	
	abstract public void deleteItems(Integer[] itemsIdArr) throws Exception;
	
	//删除商品时先判断有没有orderdetail表中是否有引用该列的数据
	abstract public void deleteItemsBefore(Integer[] itemsIdArr) throws Exception;

	int deleteByPrimaryKey(Integer id);

	int insert(Items record);

	int insertSelective(Items record);

	Items selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Items record);

	int updateByPrimaryKeyWithBLOBs(Items record);

	int updateByPrimaryKey(Items record);
}
