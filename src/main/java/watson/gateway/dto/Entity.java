package watson.gateway.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Entity {
    public String entity;
    public List<Value> values;
}
