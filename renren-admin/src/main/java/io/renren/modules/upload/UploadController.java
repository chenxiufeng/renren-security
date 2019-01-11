package io.renren.modules.upload;

import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${web.upload-path}")
    private String fileUploadPath;

    @RequestMapping("/api")
    public R upload(HttpServletRequest req, MultipartHttpServletRequest multiReq)throws IOException{
        File file = new File(fileUploadPath);
        MultipartFile multipartFile=multiReq.getFile("file");
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = UUID.randomUUID() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileUploadPath + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return R.ok().put("fileName",fileName);
    }
}
