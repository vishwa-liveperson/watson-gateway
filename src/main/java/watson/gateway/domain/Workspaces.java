package watson.gateway.domain;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;

public class Workspaces {

    Assistant assistant;
    AssistantBuilder assistantBuilder;

    public Workspaces(String apikey, String versionDate, String serviceUrl){
        assistantBuilder = new AssistantBuilder();
        assistant = assistantBuilder.buildAssistant(apikey, versionDate, serviceUrl);
    }

    public WorkspaceCollection list(){
        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();
        WorkspaceCollection workspaces = assistant.listWorkspaces(options).execute().getResult();
        return workspaces;
    }

    public Workspace create(String workspaceName, String workspaceDescription){
        CreateWorkspaceOptions options = new CreateWorkspaceOptions.Builder()
                .name(workspaceName)
                .description(workspaceDescription)
                .build();

        return assistant.createWorkspace(options).execute().getResult();
    }

    public Response<Void> delete(String workspaceId){
        DeleteWorkspaceOptions options = new DeleteWorkspaceOptions.Builder(workspaceId).build();
        return assistant.deleteWorkspace(options).execute();
    }
}
