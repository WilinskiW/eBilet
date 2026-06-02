package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddLocationRequest;
import com.portfolio.ebilet.reservations.domain.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationDto createLocation(AddLocationRequest request){
        var location = locationMapper.mapRequestToEntity(request);
        location = locationRepository.save(location);
        return locationMapper.mapToDto(location);
    }
}
