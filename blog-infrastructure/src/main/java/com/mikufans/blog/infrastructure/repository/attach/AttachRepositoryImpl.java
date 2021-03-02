package com.mikufans.blog.infrastructure.repository.attach;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import com.mikufans.blog.domain.aggregate.attach.AttachRepository;

public class AttachRepositoryImpl implements AttachRepository {

    @Override
    public PageInfo<AttachEntity> getAttachs(Integer page, Integer limit) {
        return null;
    }

    @Override
    public void save(String fname, String fkey, String ftype, Integer author) {

    }

    @Override
    public AttachEntity selectById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
