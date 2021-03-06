package com.mikufans.blog.infrastructure.repository.attach;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import com.mikufans.blog.domain.aggregate.attach.AttachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachRepositoryImpl implements AttachRepository {

    @Override
    public List<AttachPo> getAttachs(Integer page, Integer limit) {
        return null;
    }

    @Override
    public void save(AttachPo attachPo) {

    }

    @Override
    public AttachEntity selectById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
