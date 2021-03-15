package com.mikufans.blog.domain.service;

import com.mikufans.blog.infrastructure.repository.attach.AttachRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttachDomainService {
    @Resource
    private AttachRepository attachRepository;

}
