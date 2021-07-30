package watson.gateway.service;


import com.ibm.watson.assistant.v1.model.Workspace;
import watson.gateway.domain.Credentials;
import watson.gateway.domain.Entities;
import watson.gateway.dto.Entity;
import watson.gateway.domain.WorkspaceManager;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    WorkspaceManager workspaces;

    public void train(Credentials credentials) {


        String workspaceName = "Test-WorkSpace";
        String workspaceDescription = "Test Workspace Description";
        String apikey = credentials.getApikey();
        String versionDate = credentials.getVersionDate();
        String serviceUrl = credentials.getServiceUrl();
        Workspace workSpace = createWorkSpace(apikey, versionDate, serviceUrl, workspaceName, workspaceDescription);
        String workspaceId = workSpace.getWorkspaceId();
        List<Entity> entityList = mockEntityList();

        createEntity(apikey, versionDate, serviceUrl, entityList, workspaceId);

        createIntent(apikey, versionDate, serviceUrl);
    }

    //TODO : This should be passed by test
    private List<Entity> mockEntityList() {
        List<Entity> entityList = new ArrayList<>();
//        entityList.add(new Entity("Fruit", Arrays.asList("Apple", "Orange")));
        return entityList;
    }

    private void createIntent(String apikey, String versionDate, String serviceUrl) {

    }

    private void createEntity(String apikey, String versionDate, String serviceUrl, List<Entity> entityList, String workspaceId) {
        Entities entities = new Entities(apikey, versionDate, serviceUrl);
//        entityList.stream().
//                map(entity -> entities.create(workspaceId, entity.getEntityName(), entity.getEntityList()));

    }

    private Workspace createWorkSpace(String apikey, String versionDate, String serviceUrl, String workspaceName, String workspaceDescription) {
        workspaces = new WorkspaceManager(apikey, versionDate, serviceUrl);
        return workspaces.create(workspaceName, workspaceDescription);
    }
}
