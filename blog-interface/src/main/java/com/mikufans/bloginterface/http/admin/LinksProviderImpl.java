package com.mikufans.bloginterface.http.admin;

import com.mikufans.blog.application.service.MetaAppService;
import com.mikufans.blog.domain.aggregate.meta.MetaEntity;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.blog.infrastructure.common.Types;
import com.mikufans.blog.infrastructure.common.TipException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "admin/links")
@Slf4j
public class LinksProviderImpl {
    @Resource
    private MetaAppService metaAppService;

    @GetMapping(value = "")
    public String index()
    {
        HttpServletRequest request = HttpUtil.getRequest();
        List<MetaEntity> metas=metaAppService.getMetas(Types.LINK.getType());
        request.setAttribute("links",metas);
        return "admin/links";
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto saveLink(@RequestParam String title,@RequestParam String url,
                                    @RequestParam String logo,@RequestParam Integer mid,
                                    @RequestParam(value = "sort",defaultValue = "0") int sort)
    {
        //todo
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto delete(@RequestParam int mid)
    {
        try {
            metaAppService.delete(mid);
        } catch (Exception e) {
            String msg="友链删除失败";
            if(e instanceof TipException)
                msg=e.getMessage();
            else log.error(msg,e);
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }
}
