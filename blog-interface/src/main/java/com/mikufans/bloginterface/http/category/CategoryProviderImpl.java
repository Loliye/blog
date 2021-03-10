package com.mikufans.bloginterface.http.category;

import com.mikufans.blog.application.service.MetaAppService;
import com.mikufans.blog.domain.aggregate.meta.MetaEntity;
import com.mikufans.blog.infrastructure.common.WebConst;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.constant.Types;
import com.mikufans.bloginterface.common.exception.TipException;
import com.mikufans.bloginterface.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
@Slf4j
public class CategoryProviderImpl {
    @Resource
    private MetaAppService metaAppService;

    /**
     * 分类页
     * @return
     */
    @GetMapping(value = "")
    public String index()
    {
        HttpServletRequest request = HttpUtil.getRequest();
        List<MetaEntity> categories=metaAppService
                .getMetaList(Types.CATEGORY.getType(),null, WebConst.MAX_POSTS);
        List<MetaEntity> tags=metaAppService
                .getMetaList(Types.TAG.getType(),null,WebConst.MAX_POSTS);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        return "admin/category";
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto saveCategory(@RequestParam String cname,
                                        @RequestParam Integer mid)
    {
        try {
            metaAppService.saveMeta(Types.CATEGORY.getType(),cname,mid);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "分类保存失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }


    @PostMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto deleteCategory(@RequestParam int mid)
    {
        try {
            metaAppService.delete(mid);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }

}
