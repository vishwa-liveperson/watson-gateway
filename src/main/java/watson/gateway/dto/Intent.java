package watson.gateway.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intent {
    public String intent;
    public List<Example> examples;
    public String description;
}