package watson.gateway.domain;

import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;

import java.util.ArrayList;
import java.util.List;

public class IntentManager {

    Assistant assistant;
    AssistantBuilder assistantBuilder;

    public IntentManager(String apikey, String versionDate, String serviceUrl){
        assistantBuilder = new AssistantBuilder();
        assistant = assistantBuilder.buildAssistant(apikey, versionDate, serviceUrl);
    }

    public IntentCollection list(String workspaceId){
        ListIntentsOptions options = new ListIntentsOptions.Builder(workspaceId).build();
        IntentCollection response = assistant.listIntents(options).execute().getResult();
        return response;
    }

    public Intent create(String workspaceId, watson.gateway.dto.Intent intent){
        List<Example> examples = new ArrayList<>();

        List<watson.gateway.dto.Example> intentExamples = intent.getExamples();
        for(watson.gateway.dto.Example example : intentExamples){
            examples.add(new Example.Builder(example.getText()).build());
        }

        CreateIntentOptions options = new CreateIntentOptions.Builder(workspaceId, intent.getIntent())
                .examples(examples)
                .build();

        Intent response = assistant.createIntent(options).execute().getResult();

        return response;
    }

    public Intent get(String workspaceId, String intent){
        GetIntentOptions options = new GetIntentOptions.Builder(workspaceId, intent).build();

        Intent response = assistant.getIntent(options).execute().getResult();

        return response;
    }

    public Intent update(String workspaceId, String intent, List<Example> examples, String description){
        UpdateIntentOptions options = new UpdateIntentOptions.Builder(workspaceId, intent)
                .newExamples(examples)
                .newDescription(description)
                .build();

        Intent response = assistant.updateIntent(options).execute().getResult();

        return response;
    }

    public void delete(String workspaceId, String intent){
        DeleteIntentOptions options = new DeleteIntentOptions.Builder(workspaceId, intent).build();
        assistant.deleteIntent(options).execute();
    }
}
