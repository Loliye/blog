package com.mikufans.blog.application.service;

import com.mikufans.blog.application.convertor.OptionConvertor;
import com.mikufans.blog.domain.aggregate.option.OptionEntity;
import com.mikufans.blog.infrastructure.repository.option.OptionPo;
import com.mikufans.blog.infrastructure.repository.option.OptionRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OptionAppService {

    @Resource
    private OptionRespository optionRespository;

    public List<OptionEntity> getOptions() {
        List<OptionPo> options = optionRespository.getOptions();
        return OptionConvertor.optionPos2EntityList(options);
    }

    public void saveOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            options.forEach(this::insertOption);
        }
    }

    public void insertOption(String name, String value) {
        OptionPo optionPo = OptionPo.builder().name(name).value(value).build();
        OptionPo optionByName = optionRespository.getOptionByName(name);
        if (optionByName == null)
            optionRespository.insertOption(optionPo);
        else optionRespository.updateOptionByName(optionPo);
    }
}
