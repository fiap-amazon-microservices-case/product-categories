package br.com.fiap.aoj.productcategories.applications;

import br.com.fiap.aoj.productcategories.data.CategoryRepository;
import br.com.fiap.aoj.productcategories.domain.ProductId;
import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NewCategoryUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewCategoryUseCase.class);

	private final CategoryRepository categoryRepository;

	public NewCategoryUseCase(final CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Optional<CategoryDomain> add(final CategoryDomain categoryDomain){
		try{
			LOGGER.debug("m=add(categoryDomain={})", categoryDomain);

			final CategoryDomain categoryDomainUpdated = categoryRepository.findById(categoryDomain.getName())
					.map(tagFound -> append(tagFound, categoryDomain.getProducts()))
					.orElseGet(() -> categoryDomain);
			return Optional.of(categoryRepository.save(categoryDomainUpdated));
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}

	private CategoryDomain append(CategoryDomain tagFound, Set<ProductId> products) {
		final Set<ProductId> productsMerged = Stream.of(products, tagFound.getProducts())
				.flatMap(p -> p.stream()) //
				.collect(Collectors.toSet());
		return CategoryDomain //
				.builder() //
				.name(tagFound.getName()) //
				.products(productsMerged) //
				.builder();
	}
}