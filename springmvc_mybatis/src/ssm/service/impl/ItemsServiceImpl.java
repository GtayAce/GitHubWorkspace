package ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ssm.mapper.ItemsMapper;
import ssm.po.Items;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService{
	
	//注入mapper
	@Autowired
	private ItemsMapper itemsMapper;

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsMapper.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		//获取查询到的items
		Items items = itemsMapper.findItemsById(id);
		//中间进行业务处理
		//...
		
		//返回的ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		
		//将items中的属性值拷贝到ItemsCustom中
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItemsById(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验,通常在service层对关键参数进行校验
		//如果校验为空,则抛出异常
		
		//设置id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);	
	}

	@Override
	public void deleteItems(Integer[] itemsIdArr) throws Exception {
		// TODO Auto-generated method stub
		itemsMapper.deleteItems(itemsIdArr);
	}

	@Override
	public void deleteItemsBefore(Integer[] itemsIdArr) throws Exception {
		// TODO Auto-generated method stub
		itemsMapper.deleteItemsBefore(itemsIdArr);
	}

}
