package watson.gateway.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityPayload {
    public String entity;
    public List<Value> values;
}
