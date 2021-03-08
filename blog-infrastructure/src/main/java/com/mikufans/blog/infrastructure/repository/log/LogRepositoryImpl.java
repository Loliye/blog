package com.mikufans.blog.infrastructure.repository.log;

import com.mikufans.blog.domain.aggregate.log.LogEntity;
import com.mikufans.blog.domain.aggregate.log.LogRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogRepositoryImpl implements LogRespository {
    @Override
    public int addLog(LogPo logDomain) {
        return 0;
    }

    @Override
    public int deleteLogById(Integer id) {
        return 0;
    }

    @Override
    public List<LogEntity> getLogs() {
        return null;
    }
}
