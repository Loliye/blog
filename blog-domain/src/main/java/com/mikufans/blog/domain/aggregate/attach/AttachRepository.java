package com.mikufans.blog.domain.aggregate.attach;

import com.github.pagehelper.PageInfo;

public interface AttachRepository {
    /**
     * 分页查询附件
     * @param page
     * @param limit
     * @return
     */
    PageInfo<AttachEntity> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     *
     * @param fname
     * @param fkey
     * @param ftype
     * @param author
     */
    void save(String fname, String fkey, String ftype, Integer author);

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
