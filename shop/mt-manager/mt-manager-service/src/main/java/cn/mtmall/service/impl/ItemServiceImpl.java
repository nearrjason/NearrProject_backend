package cn.mtmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.mtmall.common.pojo.EasyUIDataGridResult;
import cn.mtmall.mapper.TbItemMapper;
import cn.mtmall.pojo.TbItem;
import cn.mtmall.pojo.TbItemExample;
import cn.mtmall.pojo.TbItemExample.Criteria;
import cn.mtmall.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		// 根据主键查詢
		
		//TbItem selectByPrimaryKey = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		
		Criteria criteria = example.createCriteria();
		
		
		// 设置查询条件
		criteria.andIdEqualTo(itemId);
		//執行查詢
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

}
