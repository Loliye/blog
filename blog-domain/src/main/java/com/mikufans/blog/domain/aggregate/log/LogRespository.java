package com.mikufans.blog.domain.aggregate.log;

import com.mikufans.blog.infrastructure.repository.log.LogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogRespository {
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
