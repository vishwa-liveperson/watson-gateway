package watson.gateway.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PredictResponse {
    private String text;
    private List<PredictIntent> predictIntents;
}
