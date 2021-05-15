package group2.cms.service.DTO;

import group2.cms.domain.BaseEntity;

public interface DTOConverter<E extends BaseEntity<?>, T extends EntityDTO<E>> {
    E dtoToEntity(T dto);
    T entityToDto(E entity);
}
