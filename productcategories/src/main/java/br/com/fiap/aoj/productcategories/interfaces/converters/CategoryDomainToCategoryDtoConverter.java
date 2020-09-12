package br.com.fiap.aoj.productcategories.interfaces.converters;

import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import br.com.fiap.aoj.productcategories.interfaces.dtos.CategoryDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class CategoryDomainToCategoryDtoConverter implements Converter<CategoryDomain, CategoryDto> {

	@Override
	public CategoryDto convert(final CategoryDomain source) {
		final CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(source.getName());
		categoryDto.setProductCount(source.productsCount());

		return categoryDto;
	}
}