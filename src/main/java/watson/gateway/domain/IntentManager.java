package watson.gateway.domain;

import com.ibm.watson.assistant.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import watson.gateway.dto.PredictIntent;
import watson.gateway.dto.PredictResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IntentManager {

    @Autowired
    private Credentials credentials;

    @Autowired
    private AssistantBuilder assistantBuilder;

    public IntentManager(Credentials credentials, AssistantBuilder assistantBuilder){
        this.credentials = credentials;
        this.assistantBuilder = assistantBuilder;
    }

    public IntentCollection list(String workspaceId){
        ListIntentsOptions options = new ListIntentsOptions.Builder(workspaceId).build();
        IntentCollection response = assistantBuilder.getAssistant().listIntents(options).execute().getResult();
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

        Intent response = assistantBuilder.getAssistant().createIntent(options).execute().getResult();

        return response;
    }

    public Intent get(String workspaceId, String intent){
        GetIntentOptions options = new GetIntentOptions.Builder(workspaceId, intent).build();

        Intent response = assistantBuilder.getAssistant().getIntent(options).execute().getResult();

        return response;
    }

    public Intent update(String workspaceId, String intent, List<Example> examples, String description){
        UpdateIntentOptions options = new UpdateIntentOptions.Builder(workspaceId, intent)
                .newExamples(examples)
                .newDescription(description)
                .build();

        Intent response = assistantBuilder.getAssistant().updateIntent(options).execute().getResult();

        return response;
    }

    public void delete(String workspaceId, String intent){
        DeleteIntentOptions options = new DeleteIntentOptions.Builder(workspaceId, intent).build();
        assistantBuilder.getAssistant().deleteIntent(options).execute();
    }

    public List<PredictResponse> predict(String workspaceId, List<String> textList) {
        List<PredictResponse> predictResponseList = new ArrayList<>();
        for(String text : textList){
            MessageInput input = new MessageInput();
            input.setText(text);
            MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).build();

            MessageResponse response = assistantBuilder.getAssistant().message(options).execute().getResult();
            List<PredictIntent> predictIntents = response.getIntents().
                    stream().
                    map(res -> new PredictIntent(res.intent(), res.confidence())).
                    collect(Collectors.toList());

            predictResponseList.add(new PredictResponse(text, predictIntents));
        }

        return predictResponseList;
    }
}
