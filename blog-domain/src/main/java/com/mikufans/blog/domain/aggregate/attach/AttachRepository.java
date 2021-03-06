package com.mikufans.blog.domain.aggregate.attach;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.infrastructure.repository.attach.AttachPo;

import java.util.List;

public interface AttachRepository {
    /**
     * 分页查询附件
     * @param page
     * @param limit
     * @return
     */
    List<AttachPo> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     *
     */
    void save(AttachPo attachPo);

    /**
     * 根据附件id查询附件
     * @param id
     * @return
     */
    AttachEntity selectById(Integer id);

    /**
     * 删除附件
     * @param id
     */
    void deleteById(Integer id);
}
