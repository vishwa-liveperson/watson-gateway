package watson.gateway.domain;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v1.Assistant;

public class AssistantBuilder {

    Assistant assistant;

    Assistant buildAssistant(String apikey, String versionDate, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apikey);
        assistant = new Assistant(versionDate, authenticator);
        assistant.setServiceUrl(serviceUrl);

        return assistant;
    }
}
