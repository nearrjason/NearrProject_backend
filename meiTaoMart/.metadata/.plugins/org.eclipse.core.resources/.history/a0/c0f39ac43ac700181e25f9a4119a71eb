package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoItemDesc;
import com.meitaomart.pojo.MeitaoItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoItemDescMapper {
    int countByExample(MeitaoItemDescExample example);

    int deleteByExample(MeitaoItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(MeitaoItemDesc record);

    int insertSelective(MeitaoItemDesc record);

    List<MeitaoItemDesc> selectByExampleWithBLOBs(MeitaoItemDescExample example);

    List<MeitaoItemDesc> selectByExample(MeitaoItemDescExample example);

    MeitaoItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") MeitaoItemDesc record, @Param("example") MeitaoItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") MeitaoItemDesc record, @Param("example") MeitaoItemDescExample example);

    int updateByExample(@Param("record") MeitaoItemDesc record, @Param("example") MeitaoItemDescExample example);

    int updateByPrimaryKeySelective(MeitaoItemDesc record);

    int updateByPrimaryKeyWithBLOBs(MeitaoItemDesc record);

    int updateByPrimaryKey(MeitaoItemDesc record);
}