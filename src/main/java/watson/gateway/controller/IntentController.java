package watson.gateway.controller;




import watson.gateway.domain.IntentManager;
import watson.gateway.dto.Intent;
import org.springframework.web.bind.annotation.*;
import watson.gateway.dto.IntentPayload;
import watson.gateway.util.EncodeDecode;

import java.util.List;

@RestController
@RequestMapping
public class IntentController {

    String apikey;
    String versionDate;
    String serviceUrl;
    IntentManager intentManager;

    public IntentController(){
        //Credentials - How do we pass these via REST endpoints - headers ?
        apikey = EncodeDecode.decode("MWRpWmhXckFtYnVJUWtZdEpSQmQtOHR1allWQXZuUFFjVXNuTXFZNjMzSno=");
        versionDate = EncodeDecode.decode("MjAyMS0wNy0xOA==");
        serviceUrl = "https://api.eu-gb.assistant.watson.cloud.ibm.com";

        intentManager = new IntentManager(apikey, versionDate, serviceUrl);
    }



    @PostMapping("/createIntents")
    public String createIntentAPI(@RequestBody IntentPayload intentPayload) throws Exception {
        System.out.println(intentPayload);
        createIntent(intentPayload);
        return "Valid Intent configured !!";
    }

    @DeleteMapping("/deleteIntent")
    public String deleteIntentAPI(@RequestBody Intent intent) throws Exception {
        deleteIntent(intent);
        return "Intent deleted !!";
    }

    private void deleteIntent(Intent intent) throws Exception {
//        intents.delete(intent.getWorkspaceId(), intent.getIntentName());
    }

    public void createIntent(IntentPayload intentPayload) {
        String workspaceId = intentPayload.getWorkspace();
        System.out.println("Creating intents for workspace :  " + workspaceId);
        List<Intent> intentList = intentPayload.getIntents();
//        intentList.
//                stream().
//                map(intent -> intentManager.create(workspaceId, intent.getIntent()));

        //TODO : Process Intents, Examples and Description
        //TODO : get encrypted workspace id via payload
        for(Intent intent : intentList){
            intentManager.create(intentPayload.getWorkspace(), intent.getIntent());
        }

    }
}
