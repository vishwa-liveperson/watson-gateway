package watson.gateway.controller;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.watson.assistant.v1.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import watson.gateway.domain.WorkspaceManager;
import watson.gateway.dto.WorkspacePayload;
import watson.gateway.util.EncodeDecode;

@RestController
@RequestMapping
public class WorkspaceController {

    private WorkspaceManager workspaceManager;

    @Autowired
    public WorkspaceController(WorkspaceManager workspaceManager){
        this.workspaceManager = workspaceManager;
    }

    @PostMapping("/createWorkspace")
    public String createWorkspace(@RequestBody WorkspacePayload workspacePayload) {
        Workspace workspace = workspaceManager.create(workspacePayload.getName(), workspacePayload.getDescription());
        return workspace.getWorkspaceId();
    }

    @DeleteMapping("/deleteWorkspace")
    public String deleteWorkspace(@RequestBody WorkspacePayload workspacePayload) {
        Response<Void> delete = workspaceManager.delete(workspacePayload.getId());
        return delete.getStatusCode() + "  " + delete.getStatusMessage();
    }
}
