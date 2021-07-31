package watson.gateway.domain;

import com.ibm.watson.assistant.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EntityManager {

    AssistantBuilder assistantBuilder;

    @Autowired
    public EntityManager(AssistantBuilder assistantBuilder) {
        this.assistantBuilder = assistantBuilder;
    }

    public EntityCollection list(String workspaceId) {
        ListEntitiesOptions options = new ListEntitiesOptions.Builder(workspaceId).build();

        EntityCollection response = assistantBuilder.getAssistant().listEntities(options).execute().getResult();

        return response;
    }

    public com.ibm.watson.assistant.v1.model.Entity create(String workspaceId, watson.gateway.dto.Entity entity) {
        List<CreateValue> entityValues = entity.getValues().stream().
                map(entityValue -> new CreateValue.Builder(entityValue.getValue()).build()).
                collect(Collectors.toList());


        CreateEntityOptions options = new CreateEntityOptions.Builder(workspaceId, entity.getEntity())
                .values(entityValues)
                .build();

        com.ibm.watson.assistant.v1.model.Entity result = assistantBuilder.getAssistant().createEntity(options).execute().getResult();

        return result;
    }

    public Entity get(String workspaceId, String entityName) {
        GetEntityOptions options = new GetEntityOptions.Builder(workspaceId, entityName).build();

        return assistantBuilder.getAssistant().getEntity(options).execute().getResult();
    }

    public com.ibm.watson.assistant.v1.model.Entity update(String workspaceId, String description, String entityName) {
        UpdateEntityOptions options = new UpdateEntityOptions.Builder(workspaceId, entityName)
                .newDescription(description)
                .build();

        return assistantBuilder.getAssistant().updateEntity(options).execute().getResult();

    }

    public void delete(String workspaceId, String entityName) {
        DeleteEntityOptions options = new DeleteEntityOptions.Builder(workspaceId, entityName).build();

        assistantBuilder.getAssistant().deleteEntity(options).execute();
    }
}
