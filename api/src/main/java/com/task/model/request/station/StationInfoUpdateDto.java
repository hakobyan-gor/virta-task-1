package com.task.model.request.station;

public record StationInfoUpdateDto(
        String name,
        Double latitude,
        Double longitude
) {
}