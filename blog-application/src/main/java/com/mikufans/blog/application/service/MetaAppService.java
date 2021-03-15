package com.mikufans.blog.application.service;

import com.mikufans.blog.domain.aggregate.meta.MetaEntity;
import com.mikufans.blog.domain.service.MetaDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MetaAppService {
    @Resource
    private MetaDomainService metaDomainService;

    public MetaEntity getMeta(String type,String name)
    {
        return null;
    }

    public List<MetaEntity> getMetas(String types)
    {
        return null;
    }

    public Integer countMeta(Integer id)
    {
        return null;
    }
    public void addMeta(MetaEntity metaEntity) {

    }

    public void saveMeta(String type,String name,Integer mid){

    }

    public void delete(int mid)
    {

    }

    public List<MetaEntity> getMetaList(String type, String orderby, int limit) {
        return null;
    }
}
