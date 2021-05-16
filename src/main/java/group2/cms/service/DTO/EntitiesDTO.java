package group2.cms.service.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public abstract class EntitiesDTO<T extends EntityDTO<?>> implements Serializable {
    private Collection<T> dtos = new ArrayList<>();

    public void addDTOS(T... dtos){
        this.dtos.addAll(Arrays.asList(dtos));
    }

    public Iterable<T> getDTOs(){
        return this.dtos;
    }

}
