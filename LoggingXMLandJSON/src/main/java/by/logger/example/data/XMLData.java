package by.logger.example.data;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "XMLData")
@XmlType(propOrder = {"configFileName", "executionTime", "files"})

public class XMLData {
    private String configFileName;
    private String executionTime;
    private String files;

    public String getConfigFileName() {
        return configFileName;
    }

    @XmlElement
    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    @XmlElement
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getFiles() {
        return files;
    }

    @XmlElement
    public void setFiles(String files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "JsonData [configFileName: " + configFileName + ", executionTime: " + executionTime + ", files: " + files + "]";
    }
}