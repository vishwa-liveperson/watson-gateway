package watson.gateway.domain;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceManager {

    @Autowired
    private AssistantBuilder assistantBuilder;

    public WorkspaceManager(AssistantBuilder assistantBuilder){
        this.assistantBuilder = assistantBuilder;
    }

    public WorkspaceCollection list(){
        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();
        WorkspaceCollection workspaces = assistantBuilder.getAssistant().listWorkspaces(options).execute().getResult();
        return workspaces;
    }

    public Workspace create(String workspaceName, String workspaceDescription){
        CreateWorkspaceOptions options = new CreateWorkspaceOptions.Builder()
                .name(workspaceName)
                .description(workspaceDescription)
                .build();

        return assistantBuilder.getAssistant().createWorkspace(options).execute().getResult();
    }

    public Response<Void> delete(String workspaceId){
        DeleteWorkspaceOptions options = new DeleteWorkspaceOptions.Builder(workspaceId).build();
        return assistantBuilder.getAssistant().deleteWorkspace(options).execute();
    }
}
