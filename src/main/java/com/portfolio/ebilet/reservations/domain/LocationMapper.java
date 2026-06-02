package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddLocationRequest;
import com.portfolio.ebilet.reservations.domain.dto.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface LocationMapper {

    LocationDto mapToDto(Location location);

    @Mapping(target = "sectors", ignore = true)
    Location mapRequestToEntity(AddLocationRequest request);
}
