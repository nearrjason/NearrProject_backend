package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoStockRecord;
import com.meitaomart.pojo.MeitaoStockRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoStockRecordMapper {
    int countByExample(MeitaoStockRecordExample example);

    int deleteByExample(MeitaoStockRecordExample example);

    int insert(MeitaoStockRecord record);

    int insertSelective(MeitaoStockRecord record);

    List<MeitaoStockRecord> selectByExample(MeitaoStockRecordExample example);

    int updateByExampleSelective(@Param("record") MeitaoStockRecord record, @Param("example") MeitaoStockRecordExample example);

    int updateByExample(@Param("record") MeitaoStockRecord record, @Param("example") MeitaoStockRecordExample example);
}