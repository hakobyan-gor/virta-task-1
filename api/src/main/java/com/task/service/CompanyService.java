package com.task.service;

import com.task.model.request.company.CompanyInfoUpdateDto;
import com.task.model.request.company.CompanyCreatingDto;
import com.task.util.error.exceptions.ConflictException;
import com.task.model.response.company.CompanyInfoDto;
import com.task.repository.company.CompanyRepository;
import com.task.converter.CompanyConverter;
import com.task.entity.company.Company;
import com.task.util.error.ErrorCodes;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    public void addCompany(CompanyCreatingDto dto) {
        if (companyRepository.existsByName(dto.name()))
            throw new ConflictException(ErrorCodes.Company.ALREADY_EXISTS);
        Company company = companyConverter.convertToEntity(dto);
        companyRepository.save(company);
    }

    @Transactional(readOnly = true)
    public CompanyInfoDto getCompanyByID(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return companyConverter.convertToDto(company);
    }

    @Transactional
    public void updateCompanyInfo(Long id, CompanyInfoUpdateDto dto) {
        Company company = companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        company.setName(dto.name());
        if (dto.parentCompanyID() != null) {
            Company parentCompany = companyRepository.findById(dto.parentCompanyID()).orElseThrow(NoSuchElementException::new);
            Company parentCompanyOld = company.getParentCompany();
            company.setParentCompany(parentCompany);
            parentCompanyOld.getSubCompanies().remove(company);
        }
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}