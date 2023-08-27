package com.masq.controller;

import com.masq.util.DateUtil;
import com.masq.util.UIDGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/file")
public class FileManagerController {

    private static final String BASE_PATH = "/home/masq/Pictures/upload";

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String dirStr = BASE_PATH + File.separator + DateUtil.currentDateWithCustom(DateUtil.DATE_FORM_WITHOUT_LINE);
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originName = file.getOriginalFilename();

        String suffix = originName.substring(originName.lastIndexOf(".") + 1);

        String newFileName = UIDGenerator.generate();

        File saveFile = new File(dir, newFileName + "." + suffix);
        file.transferTo(saveFile);

        String absolutePath = saveFile.getAbsolutePath();

        String secretPath = Base64.getEncoder().encodeToString(absolutePath.getBytes(StandardCharsets.UTF_8));

        return secretPath;
    }

    @GetMapping(value = "preview/{secretPath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] preview(@PathVariable String secretPath) throws IOException {
        System.out.println(secretPath);
        String filepath = new String(Base64.getDecoder().decode(secretPath), StandardCharsets.UTF_8);
        System.out.println("filepath = " + filepath);
        FileInputStream inputStream = new FileInputStream(new File(filepath));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;

    }
}
