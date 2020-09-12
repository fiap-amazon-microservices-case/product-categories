package br.com.fiap.aoj.productcategories.applications;

import br.com.fiap.aoj.productcategories.data.CategoryRepository;
import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import br.com.fiap.aoj.productcategories.domain.ProductId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddProductOnCategoryUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewCategoryUseCase.class);

	private final CategoryRepository categoryRepository;

	public AddProductOnCategoryUseCase(final CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void add(final String name, final ProductId productId){
		try{
			LOGGER.debug("m=add(name={}, productId={})", name, productId);

			categoryRepository.findById(name).ifPresent(categoryDomain -> {
				categoryDomain.add(productId);
				categoryRepository.save(categoryDomain);
			});
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
		}
	}

	//TODO: Realizar implementação de recebimento de evento

}