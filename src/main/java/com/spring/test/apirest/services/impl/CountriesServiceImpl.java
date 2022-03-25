package com.spring.test.apirest.services.impl;

import com.spring.test.apirest.models.Countries;
import com.spring.test.apirest.repositories.CountriesRepository;
import com.spring.test.apirest.services.CountriesService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Log4j2
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Page<Countries> getCountries(String name, Pageable pageable) {
        if (Strings.isBlank(name)) {
            return countriesRepository.findAll(pageable);
        } else {
            return countriesRepository.findAllByNameContainingIgnoreCase(name, pageable);
        }
    }

    @Override
    public Countries getCountryById(Integer id) {
        return countriesRepository.findById(id).orElseThrow();
    }

    @Override
    public Countries createCountry(Countries country) {
        return countriesRepository.save(country);
    }

    @Override
    public Countries updateCountry(Integer id, Countries country) {
        if (countriesRepository.existsById(id) && id.equals(country.getId())) {
            return countriesRepository.save(country);
        } else {
            log.error("The country with id {} doesn't exist. Or the id's didn't match", id);
            throw new NoSuchElementException("The country with id {" + id + "} doesn't exist. Or the id's didn't match");
        }
    }

    @Override
    public void deleteCountry(Integer id) {
        if (countriesRepository.existsById(id)) {
            countriesRepository.deleteById(id);
        } else {
            log.error("The country with id {} doesn't exist.", id);
            throw new NoSuchElementException("The country with id {" + id + "} doesn't exist.");
        }
    }
}
