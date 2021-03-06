package com.mikufans.bloginterface.http.attach;

import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.exception.TipException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("admin/attach")
@Slf4j
public class AttachProviderImpl {

    /**
     * 附件页面
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit)
    {
        return "";
    }

    /**
     * 上传文件接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "upload")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto upload(HttpServletRequest request,
                                  @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        return null;
    }


    /**
     * 删除附件
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto delete(@RequestParam Integer id, HttpServletRequest request) {
        return null;
    }

}
