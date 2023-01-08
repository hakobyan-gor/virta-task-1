package com.task.service;

import com.task.model.request.station.StationInfoUpdateDto;
import com.task.model.response.station.StationInfoDto;
import com.task.model.response.station.StationsInfoDto;
import com.task.repository.company.station.StationRepository;
import com.task.model.request.station.StationCreatingDto;
import com.task.entity.company.station.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.task.converter.StationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;
    private final StationConverter stationConverter;
    private final CompanyService companyService;
    private final EntityManager entityManager;

    public void addStation(StationCreatingDto dto) {
        Station station = stationConverter.convertToEntity(dto);
        companyService.getCompanyByID(dto.companyID());
        stationRepository.save(station);
    }

    @Transactional(readOnly = true)
    public StationInfoDto getStation(Long id) {
        Station station = getStationByID(id);
        return stationConverter.convertToDto(station);
    }

    @Transactional(readOnly = true)
    public Page<StationsInfoDto> getStationsInfoListByCompanyId(Long companyId, Pageable pageable) {
        Page<Station> page = stationRepository.findAllByCompanyId(companyId, pageable);
        return stationConverter.convertToDtoPage(page);
    }

    @Transactional
    public void updateStationInfo(Long id, StationInfoUpdateDto dto) {
        Station station = getStationByID(id);
        station.setLatitude(dto.latitude());
        station.setLongitude(dto.longitude());
        station.setName(dto.name());
    }

    @Transactional(readOnly = true)
    Station getStationByID(Long id) {
        return stationRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteStation(Long id) {
        Station station = getStationByID(id);
        stationRepository.delete(station);
    }

    public List<StationsInfoDto> getStationsByRadius(double latitude, double longitude, double radius) {
        StoredProcedureQuery spQuery =
                entityManager.createNamedStoredProcedureQuery("distance_calculator")
                        .setParameter("radius", radius)
                        .setParameter("latitudeIN", latitude)
                        .setParameter("longitudeIN", longitude);
        List<Station> stationsInfoDtoList = (List<Station>) spQuery.getResultList();
        return stationConverter.convertToDtoList(stationsInfoDtoList);
    }

}