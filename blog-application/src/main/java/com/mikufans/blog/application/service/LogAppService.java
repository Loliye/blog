package com.mikufans.blog.application.service;

import com.mikufans.blog.domain.aggregate.log.LogEntity;
import com.mikufans.blog.infrastructure.repository.log.LogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogAppService {

    @Resource
    private LogRepository logRespository;

    public void insertLog(LogEntity logEntity)
    {

    }

    public List<LogEntity> getLogs(int page,int limit)
    {
        return null;
    }
}
