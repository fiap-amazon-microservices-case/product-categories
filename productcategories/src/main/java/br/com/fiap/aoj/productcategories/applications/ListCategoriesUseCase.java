package br.com.fiap.aoj.productcategories.applications;

import br.com.fiap.aoj.productcategories.data.CategoryRepository;
import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListCategoriesUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewCategoryUseCase.class);

	private final CategoryRepository categoryRepository;

	public ListCategoriesUseCase(final CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Page<CategoryDomain> list(final int page, final int size){
		LOGGER.debug("m=list(page={}, size={})", page, size);

		final PageRequest pagination = PageRequest.of(page, size);
		return categoryRepository.findAll(pagination);
	}
}