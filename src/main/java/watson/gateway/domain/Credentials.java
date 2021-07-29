package watson.gateway.domain;

import watson.gateway.util.EncodeDecode;

public class Credentials {
    private String apikey = EncodeDecode.decode("MWRpWmhXckFtYnVJUWtZdEpSQmQtOHR1allWQXZuUFFjVXNuTXFZNjMzSno=");
    private String versionDate = EncodeDecode.decode("MjAyMS0wNy0xOA==");
    private String serviceUrl = "https://api.eu-gb.assistant.watson.cloud.ibm.com";

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
