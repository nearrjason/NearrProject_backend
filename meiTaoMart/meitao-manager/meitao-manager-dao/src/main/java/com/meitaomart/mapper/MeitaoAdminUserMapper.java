package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoAdminUser;
import com.meitaomart.pojo.MeitaoAdminUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoAdminUserMapper {
    int countByExample(MeitaoAdminUserExample example);

    int deleteByExample(MeitaoAdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoAdminUser record);

    int insertSelective(MeitaoAdminUser record);

    List<MeitaoAdminUser> selectByExample(MeitaoAdminUserExample example);

    MeitaoAdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoAdminUser record, @Param("example") MeitaoAdminUserExample example);

    int updateByExample(@Param("record") MeitaoAdminUser record, @Param("example") MeitaoAdminUserExample example);

    int updateByPrimaryKeySelective(MeitaoAdminUser record);

    int updateByPrimaryKey(MeitaoAdminUser record);
}