package watson.gateway.service;


import lombok.Getter;

import java.util.List;

@Getter
public class Entity {
    private String entityName;
    private List<String> entityList;

    public Entity(String entityName, List<String> entityList) {

        this.entityName = entityName;
        this.entityList = entityList;
    }

    public String getEntityName() {
        return entityName;
    }

    public List<String> getEntityList() {
        return entityList;
    }
}
