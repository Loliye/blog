package com.mikufans.bloginterface.common.util;

import com.mikufans.blog.infrastructure.common.DateKit;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class TaleUtil {

    public static String getFileKey(String name) {
        String prefix = "/upload" + DateKit.dateFormat(new Date(), "yyyy/MM");
        if (!new File(TaleUtil.getUploadFilePath() + prefix).exists()) {
            new File(TaleUtil.getUploadFilePath() + prefix).mkdirs();
        }
        name = StringUtils.trimToNull(name);
        if (name == null)
            return prefix + "/" + UUID.UU32() + "." + null;
        else {
            name = name.replace('\\', '/');
            name = name.substring(name.lastIndexOf("/") + 1);
            int index = name.lastIndexOf(".");
            String ext = null;
            if (index >= 0) {
                ext = StringUtils.trimToNull(name.substring(index + 1));
            }
            return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : ext);
        }
    }

    public static String getUploadFilePath() {
        String path = TaleUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        File file = new File("");
        return file.getAbsolutePath() + "/";
    }


    public static boolean isImage(InputStream imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0)
                return false;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
