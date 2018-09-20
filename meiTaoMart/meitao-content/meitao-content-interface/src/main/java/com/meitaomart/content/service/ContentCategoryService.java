package com.meitaomart.content.service;

import java.util.List;

import com.meitaomart.common.pojo.EasyUITreeNode;
import com.meitaomart.common.utils.MeitaoResult;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(long parentId);
	MeitaoResult addContentCategory(long parentId, String name);
	MeitaoResult updateContentCategory(long id, String name);
	MeitaoResult deleteContentCategory(long id);
}
