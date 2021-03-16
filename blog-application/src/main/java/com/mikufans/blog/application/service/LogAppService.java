package com.mikufans.blog.application.service;

import com.github.pagehelper.PageHelper;
import com.mikufans.blog.application.convertor.LogConvertor;
import com.mikufans.blog.domain.aggregate.log.LogEntity;
import com.mikufans.blog.infrastructure.common.WebConst;
import com.mikufans.blog.infrastructure.repository.log.LogPo;
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
        LogPo logPo = LogConvertor.logEntity2Po(logEntity);
        logRespository.addLog(logPo);
    }

    public List<LogEntity> getLogs(int page,int limit)
    {
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        PageHelper.startPage((page - 1) * limit, limit);
        List<LogEntity> logs = logRespository.getLogs();
        return logs;

    }
}
