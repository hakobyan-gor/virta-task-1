package com.task.controller;

import com.task.model.request.station.StationInfoUpdateDto;
import com.task.model.request.station.StationCreatingDto;
import com.task.model.response.station.StationsInfoDto;
import com.task.model.response.station.StationInfoDto;
import com.task.model.response.StatusEntity;
import com.task.util.success.SuccessCodes;
import com.task.service.StationService;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/stations")
@RequiredArgsConstructor
@RestController
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<StatusEntity> addStation(@Valid @RequestBody StationCreatingDto dto) {
        stationService.addStation(dto);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Station.CREATED, 201), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public StationInfoDto getStation(@PathVariable Long id) {
        return stationService.getStation(id);
    }

    @GetMapping("/company/{companyId}")
    public Page<StationsInfoDto> getStationsInfoListByCompanyId(@PathVariable(name = "companyId") Long companyId, @RequestParam int page, @RequestParam int size) {
        return stationService.getStationsInfoListByCompanyId(companyId, PageRequest.of(page, size));
    }

    @GetMapping("/filter")
    public List<StationsInfoDto> getStationsByRadius(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        return stationService.getStationsByRadius(latitude, longitude, radius);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusEntity> updateStationInfo(@PathVariable Long id, @Valid @RequestBody StationInfoUpdateDto dto) {
        stationService.updateStationInfo(id, dto);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Station.UPDATED, 200), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusEntity> deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Station.DELETED, 200), HttpStatus.OK);
    }

}