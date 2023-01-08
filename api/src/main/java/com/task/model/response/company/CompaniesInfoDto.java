package com.task.model.response.company;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompaniesInfoDto {

    private Long id;
    private String name;
    private int stationsCount;
    private Long parentCompanyId;

}