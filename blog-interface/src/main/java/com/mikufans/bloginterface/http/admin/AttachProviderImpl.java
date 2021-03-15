package com.mikufans.bloginterface.http.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mikufans.blog.application.service.AttachAppService;
import com.mikufans.blog.application.service.LogAppService;
import com.mikufans.blog.domain.aggregate.attach.AttachEntity;
import com.mikufans.blog.domain.aggregate.log.LogEntity;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.DateKit;
import com.mikufans.blog.infrastructure.repository.attach.AttachRepository;
import com.mikufans.blog.infrastructure.repository.log.LogActionEnum;
import com.mikufans.blog.infrastructure.repository.user.UserPo;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.constant.Types;
import com.mikufans.blog.infrastructure.common.WebConst;
import com.mikufans.bloginterface.common.exception.TipException;
import com.mikufans.bloginterface.common.util.Commons;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import com.mikufans.bloginterface.common.util.TaleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/attach")
@Slf4j
public class AttachProviderImpl {

    private static final String CLASSPATH = TaleUtil.getUploadFilePath();

    @Resource
    private AttachAppService attachAppService;

    @Resource
    private LogAppService logAppService;

    /**
     * 附件页面
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit) {
        PageInfo<AttachEntity> attachEntityPageInfo = attachAppService.getAttaches(page, limit);
        HttpServletRequest request = HttpUtil.getRequest();
        request.setAttribute("attachs", attachEntityPageInfo);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

    /**
     * 上传文件接口
     *
     * @return
     */
    @PostMapping(value = "upload")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto upload(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        HttpServletRequest request = HttpUtil.getRequest();
        HttpServletResponse response = HttpUtil.getResponse();
        UserEntity user = CommonUtil.getLoginUser();
        List<String> errorFiles = Lists.newArrayList();
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            for (MultipartFile file : multipartFiles) {
                String fname = file.getOriginalFilename();
                if (file.getSize() <= WebConst.MAX_FILE_SIZE) {
                    String fileKey = TaleUtil.getFileKey(fname);
                    File saveFile = new File(CLASSPATH + fileKey);
                    String fileType = TaleUtil.isImage(file.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType();
                    FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(saveFile));
                    AttachEntity entity = AttachEntity.builder().authorId(user.getUid())
                            .created(DateKit.getCurrentUnixTime())
                            .fkey(fileKey)
                            .ftype(fileType)
                            .fname(fname).build();

                    attachAppService.addAttach(entity);
                } else {
                    errorFiles.add(fname);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponseDto.fail();
        }
        return RestResponseDto.ok(errorFiles);
    }


    /**
     * 删除附件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto delete(@RequestParam Integer id) {
        HttpServletRequest request = HttpUtil.getRequest();
        try {
            AttachEntity attachEntity = attachAppService.getAttachById(id);
            if (attachEntity == null)
                return RestResponseDto.fail("不存在该附件");
            new File(CLASSPATH + attachEntity.getFkey()).delete();
            attachAppService.deleteById(id);
            LogEntity logEntity = LogEntity.builder().action(LogActionEnum.DELl_ATTACH.getAction())
                    .data(attachEntity.getFkey()).ip(request.getRemoteAddr())
                    .authorId(CommonUtil.getLoginUser().getUid())
                    .build();
            logAppService.insertLog(logEntity);
        } catch (Exception e) {
            String msg = "附件删除失败";
            if (e instanceof TipException)
                msg = e.getMessage();
            else log.error(msg, e);
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }

}
