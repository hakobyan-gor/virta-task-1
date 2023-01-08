package com.task.model.request.station;

public record StationCreatingDto(
        Double latitude,
        Double longitude,
        String name,
        Long companyID
) {
}