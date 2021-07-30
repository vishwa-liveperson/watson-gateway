package watson.gateway.domain;

import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Entities {

    Assistant assistant;
    AssistantBuilder assistantBuilder;

    public Entities(String apikey, String versionDate, String serviceUrl) {
        assistantBuilder = new AssistantBuilder();
        assistant = assistantBuilder.buildAssistant(apikey, versionDate, serviceUrl);
    }

    public EntityCollection list(String workspaceId) {
        ListEntitiesOptions options = new ListEntitiesOptions.Builder(workspaceId).build();

        EntityCollection response = assistant.listEntities(options).execute().getResult();

        return response;
    }

    public com.ibm.watson.assistant.v1.model.Entity create(String workspaceId, String entityName, List<String> entityValueList) {

//        List<CreateValue> entityValues = new ArrayList<CreateValue>();
//        entityValues.add(new CreateValue.Builder("water").build());
//        entityValues.add(new CreateValue.Builder("orange juice").build());
//        entityValues.add(new CreateValue.Builder("soda").build());

        List<CreateValue> entityValues = entityValueList.stream().
                map(entityValue -> new CreateValue.Builder(entityValue).build()).
                collect(Collectors.toList());


        CreateEntityOptions options = new CreateEntityOptions.Builder(workspaceId, entityName)
                .values(entityValues)
                .build();

        com.ibm.watson.assistant.v1.model.Entity result = assistant.createEntity(options).execute().getResult();

        return result;
    }

    public Entity get(String workspaceId, String entityName) {
        GetEntityOptions options = new GetEntityOptions.Builder(workspaceId, entityName).build();

        return assistant.getEntity(options).execute().getResult();
    }

    public com.ibm.watson.assistant.v1.model.Entity update(String workspaceId, String description, String entityName) {
        UpdateEntityOptions options = new UpdateEntityOptions.Builder(workspaceId, entityName)
                .newDescription(description)
                .build();

        return assistant.updateEntity(options).execute().getResult();

    }

    public void delete(String workspaceId, String entityName) {
        DeleteEntityOptions options = new DeleteEntityOptions.Builder(workspaceId, entityName).build();

        assistant.deleteEntity(options).execute();
    }
}
