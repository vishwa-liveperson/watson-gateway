package watson.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import watson.gateway.PredictResponse;
import watson.gateway.domain.EntityManager;
import watson.gateway.domain.IntentManager;
import watson.gateway.dto.Entity;
import watson.gateway.dto.PredictPayload;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PredictController {

    IntentManager intentManager;
    EntityManager entityManager;

    @Autowired
    public PredictController(IntentManager intentManager, EntityManager entityManager) {
        this.intentManager = intentManager;
        this.entityManager = entityManager;
    }

    @PostMapping("/predict")
    public List<PredictResponse> predict(@RequestBody PredictPayload predictPayload) throws Exception {
        System.out.println(predictPayload);
        List<String> textList = predictPayload.getTexts().stream().
                map(t -> t.getText()).collect(Collectors.toList());
        return predict(predictPayload.getWorkspace(), textList);
    }

    public List<PredictResponse> predict(String workspaceId, List<String> intentList) {
        return intentManager.predict(workspaceId, intentList);
    }

    public void createEntity(String workspaceId, List<Entity> entities) {
        for (Entity entity : entities) {
            entityManager.create(workspaceId, entity);
        }

    }
}
