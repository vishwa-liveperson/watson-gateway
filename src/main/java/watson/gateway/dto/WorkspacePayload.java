package watson.gateway.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkspacePayload {
    String Id;
    String name;
    String description;
}
