package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoItemCategory;
import com.meitaomart.pojo.MeitaoItemCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoItemCategoryMapper {
    int countByExample(MeitaoItemCategoryExample example);

    int deleteByExample(MeitaoItemCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoItemCategory record);

    int insertSelective(MeitaoItemCategory record);

    List<MeitaoItemCategory> selectByExample(MeitaoItemCategoryExample example);

    MeitaoItemCategory selectByPrimaryKey(Long id);
    
    String selectItemCategoryNameByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoItemCategory record, @Param("example") MeitaoItemCategoryExample example);

    int updateByExample(@Param("record") MeitaoItemCategory record, @Param("example") MeitaoItemCategoryExample example);

    int updateByPrimaryKeySelective(MeitaoItemCategory record);

    int updateByPrimaryKey(MeitaoItemCategory record);
}