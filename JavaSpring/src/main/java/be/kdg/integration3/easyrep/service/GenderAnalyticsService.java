package be.kdg.integration3.easyrep.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class GenderAnalyticsService {


    private Logger logger = LoggerFactory.getLogger(GenderAnalyticsService.class);


    public JSONObject getGenderAnalyticsData()  {
        try {
            ClassPathResource resource = new ClassPathResource("dataAI/genderPrediction/gender_summary.json");
            Path filePath = resource.getFile().toPath();
            logger.info("Reading the file with path: " + filePath.toAbsolutePath());

            String content = Files.readString(filePath);
            logger.info("File content: " + content); // Log the content to confirm
            return new JSONObject(content);

        }catch (Exception e){
            logger.error("Failed to fetch analytics data", e);
            return new JSONObject().put("error", "Unable to fetch analytics data");
        }
    }



}
