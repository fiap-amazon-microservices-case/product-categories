package br.com.fiap.aoj.productcategories.interfaces;

import br.com.fiap.aoj.productcategories.applications.ListCategoriesUseCase;
import br.com.fiap.aoj.productcategories.applications.NewCategoryUseCase;
import br.com.fiap.aoj.productcategories.domain.ProductId;
import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import br.com.fiap.aoj.productcategories.interfaces.converters.CategoryDomainToCategoryDtoConverter;
import br.com.fiap.aoj.productcategories.interfaces.converters.CategoryDtoToCategoryDomainConverter;
import br.com.fiap.aoj.productcategories.interfaces.dtos.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "${api.version.v1:/v1}/tags")
class CategoriesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

	private final NewCategoryUseCase newCategoryUseCase;
	private final ListCategoriesUseCase listCategoriesUseCase;
	private final CategoryDtoToCategoryDomainConverter categoryDtoToCategoryDomainConverter;
	private final CategoryDomainToCategoryDtoConverter categoryDomainToCategoryDtoConverter;

	CategoriesController(final NewCategoryUseCase newCategoryUseCase,
			final ListCategoriesUseCase listCategoriesUseCase,
			final CategoryDtoToCategoryDomainConverter categoryDtoToCategoryDomainConverter,
			final CategoryDomainToCategoryDtoConverter categoryDomainToCategoryDtoConverter) {
		this.newCategoryUseCase = newCategoryUseCase;
		this.listCategoriesUseCase = listCategoriesUseCase;
		this.categoryDtoToCategoryDomainConverter = categoryDtoToCategoryDomainConverter;
		this.categoryDomainToCategoryDtoConverter = categoryDomainToCategoryDtoConverter;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<Void> add(@RequestBody @Valid final CategoryDto categoryDto){
		LOGGER.debug("m=add(categoryDto={})", categoryDto);

		final CategoryDomain categoryDomain = categoryDtoToCategoryDomainConverter.convert(categoryDto);
		newCategoryUseCase.add(categoryDomain);

		return Mono.empty();
	}

	@GetMapping
	@ResponseStatus(OK)
	public Mono<Page<CategoryDto>> find(
			@RequestParam(name = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "size", defaultValue = "10") final Integer size){
		LOGGER.debug("m=find(page={}, size={})", page, size);

		final Page<CategoryDomain> pageOfCategoryDomain = listCategoriesUseCase.list(page, size);
		final List<CategoryDto> categoriesDto = pageOfCategoryDomain.getContent().stream()
				.map(categoryDomainToCategoryDtoConverter::convert) //
				.collect(Collectors.toList());

		return Mono.just(new PageImpl(categoriesDto, pageOfCategoryDomain.getPageable(), pageOfCategoryDomain.getTotalElements()));
	}
}