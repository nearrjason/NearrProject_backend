package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoOrder;
import com.meitaomart.pojo.MeitaoOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoOrderMapper {
    int countByExample(MeitaoOrderExample example);

    int deleteByExample(MeitaoOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(MeitaoOrder record);

    int insertSelective(MeitaoOrder record);

    List<MeitaoOrder> selectByExample(MeitaoOrderExample example);

    MeitaoOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") MeitaoOrder record, @Param("example") MeitaoOrderExample example);

    int updateByExample(@Param("record") MeitaoOrder record, @Param("example") MeitaoOrderExample example);

    int updateByPrimaryKeySelective(MeitaoOrder record);

    int updateByPrimaryKey(MeitaoOrder record);
}