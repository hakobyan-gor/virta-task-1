package com.task.model.response.station;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StationInfoDto {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;
    private Long companyID;

}