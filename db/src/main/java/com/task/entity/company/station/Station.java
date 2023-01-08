package com.task.entity.company.station;

import com.task.entity.BaseEntity;
import com.task.entity.company.Company;
import lombok.*;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "distance_calculator",
        procedureName = "distance_calculator",
        resultClasses = {Station.class},
        parameters = {
                @StoredProcedureParameter(name = "radius", mode = ParameterMode.IN, type = Double.class),
                @StoredProcedureParameter(name = "latitudeIN", mode = ParameterMode.IN, type = Double.class),
                @StoredProcedureParameter(name = "longitudeIN", mode = ParameterMode.IN, type = Double.class)
        }
)
@RequiredArgsConstructor
@Table(name = "station")
@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Station extends BaseEntity {

    private String name;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}