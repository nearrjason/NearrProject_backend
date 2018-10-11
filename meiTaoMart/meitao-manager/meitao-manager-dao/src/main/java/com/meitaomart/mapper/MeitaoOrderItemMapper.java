package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoOrderItemMapper {
    int countByExample(MeitaoOrderItemExample example);

    int deleteByExample(MeitaoOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoOrderItem record);

    int insertSelective(MeitaoOrderItem record);

    List<MeitaoOrderItem> selectByExample(MeitaoOrderItemExample example);

    MeitaoOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoOrderItem record, @Param("example") MeitaoOrderItemExample example);

    int updateByExample(@Param("record") MeitaoOrderItem record, @Param("example") MeitaoOrderItemExample example);

    int updateByPrimaryKeySelective(MeitaoOrderItem record);

    int updateByPrimaryKey(MeitaoOrderItem record);
}