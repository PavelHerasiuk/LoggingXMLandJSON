package by.logger.example;

import by.logger.example.result.JsonResultFile;
import by.logger.example.result.ResultAddable;
import by.logger.example.result.XmlResultFile;
import by.logger.example.util.PropertiesUtil;

public class Runner {

    private ResultAddable resultAddable = null;

    private final String fileXml = PropertiesUtil.SINGLETON.getProperties().getProperty("ResultFileXml");
    private final String fileJson = PropertiesUtil.SINGLETON.getProperties().getProperty("ResultFileJson");

    public void run() {
        new FileNameHandler(getResultFile(fileXml)).handle();
        new FileNameHandler(getResultFile(fileJson)).handle();
    }

    private ResultAddable getResultFile(String anotherString) {
        if (fileXml.equalsIgnoreCase(anotherString)) {
            resultAddable = new XmlResultFile();
        } else if (fileJson.equalsIgnoreCase(anotherString))
            resultAddable = new JsonResultFile();
        return resultAddable;
    }
}