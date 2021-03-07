package by.logger.example.result;

import by.logger.example.data.XMLData;
import by.logger.example.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;

import static by.logger.example.service.FileService.SINGLETON;

public class XmlResultFile implements ResultAddable {
    private static final Logger LOGGER = LogManager.getLogger(XmlResultFile.class);

    private void writeXML(long executionTime) {
        XMLData xmlData = new XMLData();
        String configName = new File("config.xml").getName();
        String time = String.valueOf(executionTime);
        String files = SINGLETON.getOLD_FILES() + Arrays.toString(new File(Constants.CONFIGS_PATH).list());
        xmlData.setConfigFileName(configName);
        xmlData.setExecutionTime(time);
        xmlData.setFiles(files);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLData.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xmlData, new File(Constants.RESULT_XML));
        } catch (JAXBException e) {
            LOGGER.error("Exception happen");
        }
    }

    @Override
    public void addResult(long executionTime) {
        writeXML(executionTime);
    }
}
