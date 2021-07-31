package watson.gateway.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainPayload {
    public String workspace;
    public List<Intent> intents;
    public List<Entity> entities;
}
