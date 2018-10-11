package com.meitaomart.mapper;

import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoBankingCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeitaoBankingCardMapper {
    int countByExample(MeitaoBankingCardExample example);

    int deleteByExample(MeitaoBankingCardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeitaoBankingCard record);

    int insertSelective(MeitaoBankingCard record);

    List<MeitaoBankingCard> selectByExample(MeitaoBankingCardExample example);

    MeitaoBankingCard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeitaoBankingCard record, @Param("example") MeitaoBankingCardExample example);

    int updateByExample(@Param("record") MeitaoBankingCard record, @Param("example") MeitaoBankingCardExample example);

    int updateByPrimaryKeySelective(MeitaoBankingCard record);

    int updateByPrimaryKey(MeitaoBankingCard record);
    
    //self defined
    int setAsDefaultByPrimaryKey(@Param("is_main") Boolean isMain, @Param("id") Long Id);
}