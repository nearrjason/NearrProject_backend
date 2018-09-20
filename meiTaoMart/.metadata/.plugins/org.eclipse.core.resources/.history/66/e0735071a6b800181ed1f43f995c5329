package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoContent;
import com.meitaomart.pojo.MeitaoContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoContentMapper {
    int countByExample(MeitaoContentExample example);

    int deleteByExample(MeitaoContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoContent record);

    int insertSelective(MeitaoContent record);

    List<MeitaoContent> selectByExampleWithBLOBs(MeitaoContentExample example);

    List<MeitaoContent> selectByExample(MeitaoContentExample example);

    MeitaoContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoContent record, @Param("example") MeitaoContentExample example);

    int updateByExampleWithBLOBs(@Param("record") MeitaoContent record, @Param("example") MeitaoContentExample example);

    int updateByExample(@Param("record") MeitaoContent record, @Param("example") MeitaoContentExample example);

    int updateByPrimaryKeySelective(MeitaoContent record);

    int updateByPrimaryKeyWithBLOBs(MeitaoContent record);

    int updateByPrimaryKey(MeitaoContent record);
}