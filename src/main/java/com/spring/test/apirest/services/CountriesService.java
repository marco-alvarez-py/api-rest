package com.spring.test.apirest.services;

import com.spring.test.apirest.models.Countries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountriesService {
    /**
     * Gets the list of all countries or filtered by name
     *
     * @param name     Name or part of the name of a Country
     * @param pageable Define how to manage the pagination
     * @return List of countries paginated
     */
    Page<Countries> getCountries(String name, Pageable pageable);

    /**
     * Gets a Country based on it's ID
     *
     * @param id Country identifier
     * @return The Country
     */
    Countries getCountryById(Integer id);

    /**
     * Creates a new Country
     *
     * @param country The Country to be created
     * @return The new Country
     */
    Countries createCountry(Countries country);

    /**
     * Update the Country
     *
     * @param id      Country identifier
     * @param country The Country to be updated
     * @return The updated Country
     */
    Countries updateCountry(Integer id, Countries country);

    /**
     * Deletes the Country based on it's ID
     *
     * @param id Country identifier
     */
    void deleteCountry(Integer id);
}
