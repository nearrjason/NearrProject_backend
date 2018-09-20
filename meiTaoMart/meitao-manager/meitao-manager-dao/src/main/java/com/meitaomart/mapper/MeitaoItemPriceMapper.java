package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoItemPrice;
import com.meitaomart.pojo.MeitaoItemPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoItemPriceMapper {
    int countByExample(MeitaoItemPriceExample example);

    int deleteByExample(MeitaoItemPriceExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(MeitaoItemPrice record);

    int insertSelective(MeitaoItemPrice record);

    List<MeitaoItemPrice> selectByExample(MeitaoItemPriceExample example);

    MeitaoItemPrice selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") MeitaoItemPrice record, @Param("example") MeitaoItemPriceExample example);

    int updateByExample(@Param("record") MeitaoItemPrice record, @Param("example") MeitaoItemPriceExample example);

    int updateByPrimaryKeySelective(MeitaoItemPrice record);

    int updateByPrimaryKey(MeitaoItemPrice record);
}