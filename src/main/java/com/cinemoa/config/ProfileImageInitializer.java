package com.cinemoa.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ProfileImageInitializer {
    private static final String TARGET_DIR = "C:/cinemoa-profile/";

    @PostConstruct
    public void copyDefaultImagesToTargetDir() {
        try {
            File targetDir = new File(TARGET_DIR);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }

            // classpath:/static/images/profile/ 내부의 모든 파일을 가져옴
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:/static/images/profile/*");

            for (Resource resource : resources) {
                String filename = resource.getFilename();
                File targetFile = new File(targetDir, filename);

                // 대상 파일이 이미 존재하면 건너뜀
                if (!targetFile.exists()) {
                    try (InputStream in = resource.getInputStream();
                         OutputStream out = new FileOutputStream(targetFile)) {
                        in.transferTo(out);
                        System.out.println("복사 완료: " + filename);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("기본 이미지 복사 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
