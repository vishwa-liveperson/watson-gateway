package watson.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import watson.gateway.domain.EntityManager;
import watson.gateway.domain.IntentManager;
import watson.gateway.dto.Entity;
import watson.gateway.dto.EntityPayload;
import watson.gateway.dto.Intent;
import watson.gateway.dto.IntentPayload;
import watson.gateway.util.EncodeDecode;

import java.util.List;

@RestController
@RequestMapping
public class EntityController {

    EntityManager entityManager;

    public EntityController(){
        String apikey = EncodeDecode.decode("MWRpWmhXckFtYnVJUWtZdEpSQmQtOHR1allWQXZuUFFjVXNuTXFZNjMzSno=");
        String versionDate = EncodeDecode.decode("MjAyMS0wNy0xOA==");
        String serviceUrl = "https://api.eu-gb.assistant.watson.cloud.ibm.com";

        entityManager = new EntityManager(apikey, versionDate, serviceUrl);
    }

    @PostMapping("/createEntities")
    public String createEntities(@RequestBody EntityPayload entityPayload) throws Exception {
        System.out.println(entityPayload);
        createEntity(entityPayload);
        return "Valid Entity configured !!";
    }

    public void createEntity(EntityPayload entityPayload) {
        String workspaceId = entityPayload.getWorkspace();
        System.out.println("Creating intents for workspace :  " + workspaceId);
        List<Entity> entities = entityPayload.getEntities();
        for(Entity entity : entities){
            entityManager.create(entityPayload.getWorkspace(), entity);
        }

    }
}
