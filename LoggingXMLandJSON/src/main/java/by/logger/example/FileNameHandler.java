package by.logger.example;

import by.logger.example.result.ResultAddable;
import by.logger.example.service.FileService;
import by.logger.example.service.TimeService;
import by.logger.example.util.Constants;
import by.logger.example.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import static by.logger.example.service.FileService.SINGLETON;

public class FileNameHandler {
    private static final Logger LOGGER = LogManager.getLogger(FileNameHandler.class);
    private final ResultAddable resultAddable;

    public FileNameHandler(ResultAddable resultAddable) {
        this.resultAddable = resultAddable;
    }

    public void handle() {
        long startTime = System.currentTimeMillis();
        LOGGER.trace("Start trace");
        LOGGER.info("Entering application.");
        Properties properties = PropertiesUtil.SINGLETON.getProperties();
        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        File oldName1 = new File(Constants.PATH1);
        File oldName2 = new File(Constants.PATH2);

        FileService.SINGLETON.checkOldName1(properties, oldName1);
        FileService.SINGLETON.renameOldName1(suffix, path, oldName1);

        FileService.SINGLETON.checkOldName2(properties, oldName2);
        FileService.SINGLETON.renameOldName2(suffix, path, oldName2);

        File files = new File(Constants.CONFIGS_PATH);
        LOGGER.info(SINGLETON.getOLD_FILES() + Arrays.toString(files.list()));
        long endTime = System.currentTimeMillis();
        long executionTime = TimeService.SINGLETON.getExecutionTime(startTime, endTime);

        resultAddable.addResult(executionTime);
        LOGGER.info("Application closed.");
        LOGGER.trace("End trace");
    }
}