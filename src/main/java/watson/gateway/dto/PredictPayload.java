package watson.gateway.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PredictPayload {
    public String workspace;
    public List<Text> texts;
}
