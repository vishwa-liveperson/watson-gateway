package watson.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import watson.gateway.domain.EntityManager;
import watson.gateway.domain.IntentManager;
import watson.gateway.dto.*;

import java.util.List;

@RestController
@RequestMapping
public class TrainController {

    IntentManager intentManager;
    EntityManager entityManager;

    @Autowired
    public TrainController(IntentManager intentManager, EntityManager entityManager) {
        this.intentManager = intentManager;
        this.entityManager = entityManager;
    }

    @PostMapping("/train")
    public String train(@RequestBody TrainPayload trainPayload) throws Exception {
        System.out.println(trainPayload);
        createEntity(trainPayload.getWorkspace(), trainPayload.getEntities());
        createIntent(trainPayload.getWorkspace(), trainPayload.getIntents());
        return "Training Done !!";
    }

    public void createIntent(String workspaceId, List<Intent> intentList) {
        for (Intent intent : intentList) {
            intentManager.create(workspaceId, intent);
        }
    }

    public void createEntity(String workspaceId, List<Entity> entities) {
        for (Entity entity : entities) {
            entityManager.create(workspaceId, entity);
        }

    }
}
