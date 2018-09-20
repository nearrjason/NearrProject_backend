package com.meitaomart.content.service;


import java.util.List;

import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoContent;

public interface ContentService {
	MeitaoResult addContent(MeitaoContent content);
	MeitaoResult deleteContents(String ids);
	EasyUIDataGridResult getContentList(Long categoryId, int page, int rows);
	List<MeitaoContent> getContentListByCid(long categoryId);
}
