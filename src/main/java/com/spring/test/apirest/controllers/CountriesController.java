package com.spring.test.apirest.controllers;

import com.spring.test.apirest.dtos.PageDto;
import com.spring.test.apirest.models.Countries;
import com.spring.test.apirest.services.CountriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.spring.test.apirest.ApiRestApplication.HTTP_200;

@RestController
@Log4j2
@Tag(name = "Countries")
@RequestMapping(value = "countries")
public class CountriesController {

    public static final String COUNTRY_IDENTIFIER = "Country identifier";
    private final CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @Operation(summary = "Gets a paginated list of countries")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = HTTP_200,
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PageCountriesDto.class)
                    )
            }
    )
    @GetMapping
    public PageDto<Countries> getCountries(@Parameter(description = "Name to filter", example = "par")
                                           @RequestParam(required = false) String name,
                                           @Parameter(description = "Starting page number", example = "0")
                                           @RequestParam(defaultValue = "0") int page,
                                           @Parameter(description = "Size of items per page", example = "3")
                                           @RequestParam(defaultValue = "3") int size) {

        log.info("{}.{} IN {} {}, {} {}, {} {}",
                CountriesController.class.getSimpleName(), "getCountries",
                "<name>:", name,
                "<page>:", page,
                "<size>:", size);
        PageDto<Countries> countriesPage = new PageDto<>(
                countriesService.getCountries(name, PageRequest.of(page, size))
        );
        log.info("{}.{} OUT {} ", CountriesController.class.getSimpleName(), "getCountries", countriesPage);
        return countriesPage;
    }

    @Operation(summary = "Gets a Country based on the Id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = HTTP_200,
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Countries.class)
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public Countries getCountryById(@Parameter(description = COUNTRY_IDENTIFIER, example = "4")
                                    @PathVariable Integer id) {
        log.info("{}.{} IN {} {}",
                CountriesController.class.getSimpleName(), "getCountryById",
                "<id>", id);
        Countries country = countriesService.getCountryById(id);
        log.info("{}.{} OUT {} ", CountriesController.class.getSimpleName(), "getCountryById", country);
        return country;
    }

    @Operation(summary = "Creates a new Country")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = HTTP_200,
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Countries.class)
                    )
            }
    )
    @PostMapping
    public Countries createCountry(@RequestBody @Valid Countries country) {
        log.info("{}.{} IN {} {}",
                CountriesController.class.getSimpleName(), "createCountry",
                "<country>", country);
        Countries createdCountry = countriesService.createCountry(country);
        log.info("{}.{} OUT {} ", CountriesController.class.getSimpleName(), "createCountry", createdCountry);
        return createdCountry;
    }

    @Operation(summary = "Updates an existing Country")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = HTTP_200,
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Countries.class)
                    )
            }
    )
    @PutMapping("/{id}")
    public Countries updateCountry(@Parameter(description = COUNTRY_IDENTIFIER, example = "4")
                                   @PathVariable Integer id,
                                   @RequestBody @Valid Countries country) {
        log.info("{}.{} IN {} {}, {} {}",
                CountriesController.class.getSimpleName(), "updateCountry",
                "<id>", id,
                "<country>", country);
        Countries updatedCountry = countriesService.updateCountry(id, country);
        log.info("{}.{} OUT {} ", CountriesController.class.getSimpleName(), "updateCountry", updatedCountry);
        return updatedCountry;
    }

    @Operation(summary = "Deletes a Country")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = HTTP_200,
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(hidden = true)
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteCountry(@Parameter(description = COUNTRY_IDENTIFIER, example = "4")
                              @PathVariable Integer id) {
        log.info("{}.{} IN {} {}",
                CountriesController.class.getSimpleName(), "deleteCountry",
                "<id>", id);
        countriesService.deleteCountry(id);
        log.info("{}.{} OUT OK ", CountriesController.class.getSimpleName(), "deleteCountry");
    }

    // Workaround for swagger response documentation
    private static class PageCountriesDto extends PageDto<Countries> {
    }
}
