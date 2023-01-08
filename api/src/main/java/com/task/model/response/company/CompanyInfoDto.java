package com.task.model.response.company;

import com.task.model.response.station.StationsInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CompanyInfoDto {

    private Long id;
    private String name;
    private Long parentCompanyId;
    private List<StationsInfoDto> stationsInfoDtoList;
    private List<CompanyInfoDto> subCompaniesInfoDtoList;

}