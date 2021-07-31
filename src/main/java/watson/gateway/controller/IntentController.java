package watson.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import watson.gateway.domain.Credentials;
import watson.gateway.domain.IntentManager;
import watson.gateway.dto.Intent;
import org.springframework.web.bind.annotation.*;
import watson.gateway.dto.IntentPayload;
import watson.gateway.util.EncodeDecode;

import java.util.List;

@RestController
@RequestMapping
public class IntentController {
    private IntentManager intentManager;

    @Autowired
    public IntentController(Credentials credentials, IntentManager intentManager){
        this.intentManager = intentManager;
    }

    @PostMapping("/createIntents")
    public String createIntentAPI(@RequestBody IntentPayload intentPayload) throws Exception {
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
        for(Intent intent : intentList){
            intentManager.create(intentPayload.getWorkspace(), intent);
        }

    }
}
