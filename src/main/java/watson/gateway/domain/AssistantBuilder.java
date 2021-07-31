package watson.gateway.domain;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v1.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssistantBuilder {

    private Assistant assistant;

    @Autowired
    private Credentials credentials;

    public AssistantBuilder(Credentials credentials){
        this.credentials = credentials;
        IamAuthenticator authenticator = new IamAuthenticator(credentials.getApikey());
        assistant = new Assistant(credentials.getVersionDate(), authenticator);
        assistant.setServiceUrl(credentials.getServiceUrl());
    }

    public Assistant getAssistant() {
        return assistant;
    }

    Assistant buildAssistant(String apikey, String versionDate, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apikey);
        assistant = new Assistant(versionDate, authenticator);
        assistant.setServiceUrl(serviceUrl);

        return assistant;
    }
}
