package be.kdg.integration3.easyrep.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Service
public class GenderAnalyticsService {

    private Logger logger = LoggerFactory.getLogger(GenderAnalyticsService.class);
    @Autowired
    private ResourceLoader resourceLoader;

    public JSONObject getGenderAnalyticsData() {
        try {
            Resource resource = resourceLoader.getResource("classpath:dataAI/genderPrediction/gender_summary.json");
            InputStream inputStream = resource.getInputStream();
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            logger.info("File content: " + content);
            return new JSONObject(content);

        } catch (Exception e) {
            logger.error("Failed to fetch analytics data", e);
            return new JSONObject().put("error", "Unable to fetch analytics data");
        }
    }
}
