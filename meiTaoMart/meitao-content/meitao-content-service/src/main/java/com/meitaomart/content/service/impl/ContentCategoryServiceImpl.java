package com.meitaomart.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meitaomart.common.pojo.EasyUITreeNode;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.content.service.ContentCategoryService;
import com.meitaomart.mapper.MeitaoContentCategoryMapper;
import com.meitaomart.pojo.MeitaoContentCategory;
import com.meitaomart.pojo.MeitaoContentCategoryExample;
import com.meitaomart.pojo.MeitaoContentCategoryExample.Criteria;

/**
 * 内容分类管理Service
 * @author anluo
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private MeitaoContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		// 根据parentId查询子节点列表
		MeitaoContentCategoryExample example = new MeitaoContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<MeitaoContentCategory> categoryList = contentCategoryMapper.selectByExample(example);
		// 转换成EasyUITreeNode的列表
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (MeitaoContentCategory meitaoContentCategory : categoryList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(meitaoContentCategory.getId());
			node.setText(meitaoContentCategory.getName());
			node.setState(meitaoContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			nodeList.add(node);
		}

		return nodeList;
	}

	@Override
	public MeitaoResult addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
		MeitaoContentCategory contentCategory = new MeitaoContentCategory();
		//设置pojo的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1(正常), 2(刪除)
		contentCategory.setStatus(1);
		//默認排序是1 
		contentCategory.setSortOrder(1);
		//新添加的節點一定是葉子節點
		contentCategory.setIsParent(false);
		contentCategory.setCreatedTime(new Date());
		contentCategory.setUpdatedTime(new Date());

		//插入到数据库
		contentCategoryMapper.insert(contentCategory);
		Long id = contentCategory.getId();

		//判断父节点的isparent属性，如果不是true改为true
		//根據parentid查詢父節點
		MeitaoContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			//更新到數據庫中
			contentCategoryMapper.updateByPrimaryKey(parent);
		}

		//返回结果，返回MeitaoResult, 包含pojo
		return MeitaoResult.ok(contentCategory);
	}
	
	@Override
	public MeitaoResult updateContentCategory(long id, String name) {
		// TODO Auto-generated method stub
		MeitaoContentCategory current = contentCategoryMapper.selectByPrimaryKey(id);
		current.setName(name);
		contentCategoryMapper.updateByPrimaryKey(current);
		return MeitaoResult.ok(current);
	}

	@Override
	public MeitaoResult deleteContentCategory(long id) {		
		// 根据parentId查询子节点列表
		MeitaoContentCategoryExample example = new MeitaoContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(id);
		//查询子节点个数
		int numberOfNodes = contentCategoryMapper.countByExample(example);
		
		if (numberOfNodes == 0) { 
			Long parentId = contentCategoryMapper.selectByPrimaryKey(id).getParentId();
			contentCategoryMapper.deleteByPrimaryKey(id);
			
			if (isEmptyParent(parentId)) {
				MeitaoContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
				parent.setIsParent(false);
				//更新到數據庫中
				contentCategoryMapper.updateByPrimaryKey(parent);
			}
			
			return MeitaoResult.ok();
		} else {
			return MeitaoResult.build(null, "Cannot delete a non empty directory");
		}
	}
	
	private boolean isEmptyParent(long id) {
		MeitaoContentCategoryExample example = new MeitaoContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(id);
		//查询子节点个数
		return contentCategoryMapper.countByExample(example) == 0;
	}

	

}
