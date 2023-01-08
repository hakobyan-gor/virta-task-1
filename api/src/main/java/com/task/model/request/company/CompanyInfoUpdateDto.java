package com.task.model.request.company;

public record CompanyInfoUpdateDto(
        String name,
        Long parentCompanyID
) {
}
