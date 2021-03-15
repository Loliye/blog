package com.mikufans.bloginterface.http.admin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mikufans.blog.application.service.LogAppService;
import com.mikufans.blog.application.service.OptionAppService;
import com.mikufans.blog.application.service.SiteAppService;
import com.mikufans.blog.domain.aggregate.option.OptionEntity;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.exception.TipException;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/setting")
@Slf4j
public class SettingProviderImpl {
    @Resource
    private LogAppService logAppService;

    @Resource
    private SiteAppService siteAppService;

    @Resource
    private OptionAppService optionAppService;

    @GetMapping("")
    public String setting() {
        HttpServletRequest request = HttpUtil.getRequest();
        List<OptionEntity> optionEntities = optionAppService.getOptions();
        Map<String, String> options = Maps.newHashMap();
        optionEntities.stream().forEach(item -> options.put(item.getName(), item.getValue()));
        request.setAttribute("options", options);
        return "admin/setting";
    }

    @PostMapping(value = "")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto saveSetting(@RequestParam(required = false) String siteTheme) {
        try {
            HttpServletRequest request = HttpUtil.getRequest();
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> queryAns = Maps.newHashMap();
            parameterMap.forEach((key, value) -> {
                queryAns.put(key, Lists.newArrayList(value).stream().collect(Collectors.joining(",")));
            });

            optionAppService.saveOptions(queryAns);
            if (StringUtils.isNotBlank(siteTheme)) {

            }
            logAppService.insertLog(null);
            return RestResponseDto.ok();
        } catch (Exception e) {
            String msg = "保存设置失败";
            if (e instanceof TipException)
                msg = e.getMessage();
            else log.error(msg, e);
            return RestResponseDto.fail();
        }
    }

    @PostMapping(value = "backup")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto backup(@RequestParam String bk_type,@RequestParam String bk_path)
    {
        if(StringUtils.isBlank(bk_type))
            return RestResponseDto.fail("请确认信息输入完整");


        //todo
        return null;
    }

}
