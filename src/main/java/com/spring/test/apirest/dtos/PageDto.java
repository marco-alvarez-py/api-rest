package com.spring.test.apirest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Response for paginated list")
public class PageDto<T> {
    @Schema(description = "List of elements")
    private List<T> content;
    @Schema(description = "Current page during pagination", example = "2")
    private int currentPage;
    @Schema(description = "Total number of items", example = "35")
    private long totalItems;
    @Schema(description = "Total of pages available for pagination", example = "5")
    private int totalPages;

    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
