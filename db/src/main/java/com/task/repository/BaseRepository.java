package com.task.repository;

import com.task.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Here I am adding some general methods for the repository layer
 * which can be extended and used in other entities' repositories
 * @param <E>
 */
@NoRepositoryBean
public interface BaseRepository <E extends BaseEntity> extends JpaRepository<E, Long> {

    Page<E> findAll(Specification<E> specification, Pageable page);

    List<E> findAll(Specification<E> specification);

    List<E> findALLByIdIn(List<Long> ids);

}