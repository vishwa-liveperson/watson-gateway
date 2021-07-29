package watson.gateway.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intent {
    private String intentName;
    private String workspaceId;
    private List<IntentText> intentTexts;
}