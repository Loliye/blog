package com.mikufans.blog.application.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mikufans.blog.application.service.convertor.AttachConvertor;
import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import com.mikufans.blog.domain.aggregate.attach.AttachRepository;
import com.mikufans.blog.infrastructure.common.DateKit;
import com.mikufans.blog.infrastructure.repository.attach.AttachPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachAppService {
    @Resource
    private AttachRepository attachRepository;

    public PageInfo<AttachEntity> getAttaches(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<AttachPo> attaches = attachRepository.getAttachs(page, limit);

        PageInfo<AttachEntity> attachEntityPageInfo=
                new PageInfo<>(AttachConvertor.attachPos2Entites(attaches));
        return attachEntityPageInfo;
    }

    public void addAttach(AttachEntity entity)
    {
        if(entity==null)
            throw new RuntimeException("参数错误！！！");

        AttachPo attachPo=AttachConvertor.entity2Po(entity);
        attachRepository.save(attachPo);
    }
}
