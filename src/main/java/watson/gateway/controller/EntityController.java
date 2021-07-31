package watson.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import watson.gateway.domain.EntityManager;
import watson.gateway.dto.Entity;
import watson.gateway.dto.EntityPayload;

import java.util.List;

@RestController
@RequestMapping
public class EntityController {

    EntityManager entityManager;

    @Autowired
    public EntityController(EntityManager entityManager){
        this.entityManager = entityManager;
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
