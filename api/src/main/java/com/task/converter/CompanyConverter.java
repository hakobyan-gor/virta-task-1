package com.task.converter;

import com.task.entity.company.Company;
import com.task.model.request.company.CompanyCreatingDto;
import com.task.model.response.company.CompanyInfoDto;
import com.task.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CompanyConverter {

    private final CompanyRepository repository;
    private final StationConverter stationConverter;

    public Company convertToEntity(CompanyCreatingDto dto) {
        return Company
                .builder()
                .name(dto.name())
                .parentCompany(repository.findById(dto.parentCompanyID()).orElseThrow(NoSuchElementException::new))
                .build();
    }

    public CompanyInfoDto convertToDto(Company company) {
        return CompanyInfoDto
                .builder()
                .id(company.getId())
                .name(company.getName())
                .parentCompanyId(company.getParentCompany() != null ? company.getParentCompany().getId() : null)
                .stationsInfoDtoList(company.getStations() != null ? stationConverter.convertToDtoList(company.getStations()) : null)
                .subCompaniesInfoDtoList(company.getSubCompanies() != null ? convertToDtoList(company.getSubCompanies()) : null)
                .build();
    }

    private List<CompanyInfoDto> convertToDtoList(Set<Company> subCompanies) {
        return subCompanies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}