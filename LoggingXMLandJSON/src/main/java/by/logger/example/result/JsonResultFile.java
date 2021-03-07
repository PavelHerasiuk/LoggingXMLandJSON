package by.logger.example.result;

import by.logger.example.data.JsonData;
import by.logger.example.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static by.logger.example.service.FileService.SINGLETON;

public class JsonResultFile implements ResultAddable{
    private static final Logger LOGGER = LogManager.getLogger(JsonResultFile.class);

    private void writeJson(long executionTime) {
        JsonData jsonData = new JsonData();
        String configName = new File("config.xml").getName();
        String time = String.valueOf(executionTime);
        String files = SINGLETON.getOLD_FILES() + Arrays.toString(new File(Constants.CONFIGS_PATH).list());
        jsonData.setConfigFileName(configName);
        jsonData.setExecutionTime(time);
        jsonData.setFiles(files);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(jsonData);
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception happen!", e);
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(Constants.RESULT_JSON));
        } catch (IOException e) {
            LOGGER.error("Exception happen!", e);
        }
        try {
            assert bufferedWriter != null;
            assert jsonString != null;
            bufferedWriter.write(jsonString);
            bufferedWriter.flush();
        } catch (IOException e) {
            LOGGER.error("Exception happen!", e);
        }
    }

    @Override
    public void addResult(long executionTime) {
        writeJson(executionTime);
    }
}