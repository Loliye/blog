package com.mikufans.blog.application.convertor;

import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import com.mikufans.blog.infrastructure.repository.attach.AttachPo;

import java.util.List;
import java.util.stream.Collectors;

public class AttachConvertor {
    public static AttachEntity attachPo2Entity(AttachPo attachPo)
    {
        return null;
    }

    public static List<AttachEntity> attachPos2Entites(List<AttachPo> attachPos)
    {
       return attachPos.stream().map(AttachConvertor::attachPo2Entity).
               collect(Collectors.toList());
    }
    public static AttachPo entity2Po(AttachEntity entity)
    {
        return null;
    }
}
