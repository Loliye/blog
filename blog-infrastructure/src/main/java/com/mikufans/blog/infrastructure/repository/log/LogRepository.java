package com.mikufans.blog.infrastructure.repository.log;

import com.mikufans.blog.domain.aggregate.log.LogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface LogRepository {
    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(LogPo logDomain);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     * @return
     */
    List<LogEntity> getLogs();
}
