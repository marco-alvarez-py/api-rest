package com.spring.test.apirest.repositories;

import com.spring.test.apirest.models.Countries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {
    /**
     * Gets the list of countries that match with the name
     *
     * @param name     Name or part of the name to be matched
     * @param pageable Define how to manage the pagination
     * @return List of countries paginated
     */
    Page<Countries> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
