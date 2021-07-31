package watson.gateway.domain;

import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;

public class Examples {

    Assistant assistant;
    AssistantBuilder assistantBuilder;

    public Examples(String apikey, String versionDate, String serviceUrl){
//        assistantBuilder = new AssistantBuilder();
//        assistant = assistantBuilder.buildAssistant(apikey, versionDate, serviceUrl);
    }

    public ExampleCollection list(String workspaceId, String intent){
        ListExamplesOptions options = new ListExamplesOptions.Builder(workspaceId, intent).build();

        ExampleCollection response = assistant.listExamples(options).execute().getResult();
        return response;
    }

    public Example create(String workspaceId, String intent, String example){
        CreateExampleOptions options = new CreateExampleOptions.Builder(workspaceId, intent, example).build();

        Example response = assistant.createExample(options).execute().getResult();

        return response;
    }

    public Example get(String workspaceId, String intent, String example){
        GetExampleOptions options = new GetExampleOptions.Builder(workspaceId, intent, example).build();

        Example response = assistant.getExample(options).execute().getResult();

        return response;
    }

    public Example update(String workspaceId, String intent, String example, String newText){
        UpdateExampleOptions options = new UpdateExampleOptions.Builder(workspaceId, intent, example)
                .newText(newText)
                .build();

        Example response = assistant.updateExample(options).execute().getResult();

        return response;
    }

    public void delete(String workspaceId, String intent, String example){
        DeleteExampleOptions options = new DeleteExampleOptions.Builder(workspaceId, intent, example).build();
        assistant.deleteExample(options).execute();
    }
}
