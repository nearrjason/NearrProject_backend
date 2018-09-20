package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.pojo.MeitaoUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoUserMapper {
    int countByExample(MeitaoUserExample example);

    int deleteByExample(MeitaoUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoUser record);

    int insertSelective(MeitaoUser record);

    List<MeitaoUser> selectByExample(MeitaoUserExample example);

    MeitaoUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoUser record, @Param("example") MeitaoUserExample example);

    int updateByExample(@Param("record") MeitaoUser record, @Param("example") MeitaoUserExample example);

    int updateByPrimaryKeySelective(MeitaoUser record);

    int updateByPrimaryKey(MeitaoUser record);
}