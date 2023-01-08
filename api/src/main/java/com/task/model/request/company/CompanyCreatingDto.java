package com.task.model.request.company;

public record CompanyCreatingDto(
        String name,
        Long parentCompanyID
) {
}