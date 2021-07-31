package watson.gateway.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PredictIntent {
    private String intent;
    private Double confidence;
}
