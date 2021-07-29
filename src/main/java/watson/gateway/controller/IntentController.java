package watson.gateway.controller;




import watson.gateway.domain.Intents;
import watson.gateway.dto.Intent;
import org.springframework.web.bind.annotation.*;
import watson.gateway.util.EncodeDecode;

@RestController
@RequestMapping
public class IntentController {

    String apikey;
    String versionDate;
    String serviceUrl;
    Intents intents;

    public IntentController(){
        //Credentials - How do we pass these via REST endpoints - headers ?
        apikey = EncodeDecode.decode("MWRpWmhXckFtYnVJUWtZdEpSQmQtOHR1allWQXZuUFFjVXNuTXFZNjMzSno=");
        versionDate = EncodeDecode.decode("MjAyMS0wNy0xOA==");
        serviceUrl = "https://api.eu-gb.assistant.watson.cloud.ibm.com";

        intents = new Intents(apikey, versionDate, serviceUrl);
    }



    @PostMapping("/createIntents")
    public String createIntentAPI(@RequestBody Intent intent) throws Exception {
        System.out.println(intent);
        createIntent(intent);
        return "Valid Intent configured !!";
    }

    @DeleteMapping("/deleteIntent")
    public String deleteIntentAPI(@RequestBody Intent intent) throws Exception {
        deleteIntent(intent);
        return "Intent deleted !!";
    }

    private void deleteIntent(Intent intent) throws Exception {
        intents.delete(intent.getWorkspaceId(), intent.getIntentName());
    }

    public void createIntent(Intent intent) throws Exception {
        intents.create(intent.getWorkspaceId(), intent.getIntentName());
    }
}
