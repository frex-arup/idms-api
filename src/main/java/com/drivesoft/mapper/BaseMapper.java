package com.drivesoft.mapper;

import com.drivesoft.dto.BaseDTO;
import com.drivesoft.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @param <E> stands for Entity Class
 * @param <D> stands for Dto class
 */
public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {
    D toDto(E e);

    E toEntity(D d);

    default List<D> toDtoList(List<E> entities) {

        if (Objects.nonNull(entities)) {
            return entities.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    default List<E> toEntityList(List<D> dtoList) {
        if (Objects.nonNull(dtoList)) {
            return dtoList.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    default void setAuditColumn(E baseEntity, D baseDTO) {
        baseDTO.setId(baseEntity.getId());
        baseDTO.setCreatedOn(baseEntity.getCreatedOn());
        baseDTO.setCreatedBy(baseEntity.getCreatedBy());
        baseDTO.setUpdatedOn(baseEntity.getUpdatedOn());
        baseDTO.setUpdatedBy(baseEntity.getUpdatedBy());
    }

}
