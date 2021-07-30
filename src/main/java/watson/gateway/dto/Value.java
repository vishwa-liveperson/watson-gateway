package watson.gateway.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Value{
    public String type;
    public String value;
    public List<String> synonyms;
}