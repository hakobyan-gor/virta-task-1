package com.task.controller;

import com.task.model.request.company.CompanyInfoUpdateDto;
import com.task.model.request.company.CompanyCreatingDto;
import com.task.model.response.company.CompaniesInfoDto;
import com.task.model.response.company.CompanyInfoDto;
import com.task.model.response.StatusEntity;
import com.task.service.CompanyService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import com.task.util.success.SuccessCodes;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RequestMapping("/companies")
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<StatusEntity> addCompany(@Valid @RequestBody CompanyCreatingDto dto) {
        companyService.addCompany(dto);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Company.CREATED, 201), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CompanyInfoDto getCompany(@PathVariable Long id) {
        return companyService.getCompanyByID(id);
    }

    @GetMapping
    public Page<CompaniesInfoDto> getCompaniesInfoList() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusEntity> updateCompanyInfo(@PathVariable Long id, @Valid @RequestBody CompanyInfoUpdateDto dto) {
        companyService.updateCompanyInfo(id, dto);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Company.UPDATED, 200), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusEntity> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(new StatusEntity(SuccessCodes.Company.DELETED, 200), HttpStatus.OK);
    }

}