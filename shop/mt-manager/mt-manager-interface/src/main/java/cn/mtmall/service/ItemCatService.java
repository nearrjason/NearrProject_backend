package cn.mtmall.service;

import java.util.List;

import cn.mtmall.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatlist(long parentId);
}
