package com.mikufans.blog.infrastructure.repository.attach;

import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachMapper {
    long countByExample(AttachPo example);


    int deleteByPrimaryKey(Integer id);

    int insert(AttachPo record);


    List<AttachPo> selectAll(AttachPo example);

    AttachPo selectByPrimaryKey(Integer id);




    int updateByPrimaryKey(AttachPo record);
}
