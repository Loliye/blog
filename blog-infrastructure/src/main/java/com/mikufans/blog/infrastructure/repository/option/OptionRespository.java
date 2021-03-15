package com.mikufans.blog.infrastructure.repository.option;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionRespository {
    int deleteOptionByName(@Param("name") String name);

    int updateOptionByName(OptionPo optionPo);

    OptionPo getOptionByName(@Param("name") String name);

    List<OptionPo> getOptions();
}
