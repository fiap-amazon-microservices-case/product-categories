package br.com.fiap.aoj.productcategories.interfaces.converters;

import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import br.com.fiap.aoj.productcategories.interfaces.dtos.CategoryDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Lazy
@Component
public class CategoryDtoToCategoryDomainConverter implements Converter<CategoryDto, CategoryDomain> {

	@Override
	public CategoryDomain convert(final CategoryDto source) {
		return CategoryDomain //
				.builder() //
				.name(source.getName()) //
				.builder();
	}
}