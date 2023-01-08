package com.task.model.response.station;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StationsInfoDto {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Long companyID;

}