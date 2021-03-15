package com.mikufans.blog.infrastructure.repository.attach;

import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachRepository {

    /**
     * 分页查询附件
     *
     * @param page
     * @param limit
     * @return
     */
    List<AttachPo> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     */
    void save(AttachPo attachPo);

    /**
     * 根据附件id查询附件
     *
     * @param id
     * @return
     */
    AttachEntity selectById(Integer id);

    /**
     * 删除附件
     *
     * @param id
     */
    void deleteById(Integer id);
}
