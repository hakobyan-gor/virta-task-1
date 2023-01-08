package com.task.converter;

import com.task.entity.company.station.Station;
import com.task.model.request.station.StationCreatingDto;
import com.task.model.response.station.StationInfoDto;
import com.task.model.response.station.StationsInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationConverter {

    public List<StationsInfoDto> convertToDtoList(List<Station> stations) {
        return stations.stream().map(this::convertToDtoListItem).collect(Collectors.toList());
    }

    private StationsInfoDto convertToDtoListItem(Station station) {
        return StationsInfoDto
                .builder()
                .longitude(station.getLongitude())
                .latitude(station.getLatitude())
                .name(station.getName())
                .id(station.getId())
                .build();
    }

    public Station convertToEntity(StationCreatingDto dto) {
        return Station
                .builder()
                .longitude(dto.longitude())
                .latitude(dto.latitude())
                .name(dto.name())
                .build();
    }

    public StationInfoDto convertToDto(Station station) {
        return StationInfoDto
                .builder()
                .companyID(station.getCompany().getId())
                .longitude(station.getLongitude())
                .latitude(station.getLatitude())
                .name(station.getName())
                .id(station.getId())
                .build();
    }

    public Page<StationsInfoDto> convertToDtoPage(Page<Station> page) {
        return page.map(this::convertToDtoPageItem);
    }

    private StationsInfoDto convertToDtoPageItem(Station station) {
        return StationsInfoDto
                .builder()
                .companyID(station.getCompany().getId())
                .longitude(station.getLongitude())
                .latitude(station.getLatitude())
                .name(station.getName())
                .id(station.getId())
                .build();
    }

}