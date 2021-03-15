package com.mikufans.blog.application.service;

import com.mikufans.blog.domain.aggregate.option.OptionEntity;
import com.mikufans.blog.infrastructure.repository.option.OptionRespsitory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OptionAppService {

    public List<OptionEntity> getOptions()
    {
        return null;
    }

    public void saveOptions(Map<String,String> options)
    {

    }
}
