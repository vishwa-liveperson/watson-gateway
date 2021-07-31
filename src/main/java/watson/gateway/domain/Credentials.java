package watson.gateway.domain;

import org.springframework.stereotype.Component;
import watson.gateway.util.EncodeDecode;

@Component
public class Credentials {
    private String apikey;
    private String versionDate;
    private String serviceUrl;

    public Credentials(){
        apikey = EncodeDecode.decode("MWRpWmhXckFtYnVJUWtZdEpSQmQtOHR1allWQXZuUFFjVXNuTXFZNjMzSno=");
        versionDate = EncodeDecode.decode("MjAyMS0wNy0xOA==");
        serviceUrl = "https://api.eu-gb.assistant.watson.cloud.ibm.com";
    }

    public String getApikey() {
        return apikey;
    }

    public String getVersionDate() {
        return versionDate;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }


}
