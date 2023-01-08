package com.task.repository.company.station;

import com.task.entity.company.station.Station;
import com.task.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends BaseRepository<Station> {

    Page<Station> findAllByCompanyId(Long companyId, Pageable pageable);

}