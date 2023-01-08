package com.task.entity.company;

import com.task.entity.BaseEntity;
import com.task.entity.company.station.Station;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Table(name = "company")
@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Company extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_company_id")
    private Company parentCompany;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Station> stations = new ArrayList<>();

    @OneToMany(mappedBy = "parentCompany", cascade = CascadeType.ALL)
    private Set<Company> subCompanies;

}