package br.com.fiap.aoj.productcategories.listeners;


import br.com.fiap.aoj.productcategories.applications.AddProductOnCategoryUseCase;
import br.com.fiap.aoj.productcategories.domain.ProductId;
import br.com.fiap.aoj.productcategories.listeners.dtos.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
class CreatedEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreatedEventListener.class);

	private final AddProductOnCategoryUseCase addProductOnCategoryUseCase;

	CreatedEventListener(final AddProductOnCategoryUseCase addProductOnCategoryUseCase) {
		this.addProductOnCategoryUseCase = addProductOnCategoryUseCase;
	}

	@SqsListener(value = "${aws.sqs.created-product}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void createdProductEventListener(ItemDto itemDto){
		LOGGER.info("m=createdProductEventListener(itemDto={})", itemDto);

		if(isNull(itemDto.getProductId()) || isNull(itemDto.getCategoryId())){
			LOGGER.warn("Dados não informados, ID do produto={} ou ID da categoria={}", itemDto.getProductId(), itemDto.getCategoryId());
			return;
		}

		addProductOnCategoryUseCase.add(itemDto.getCategoryId(), ProductId.of(itemDto.getProductId()));
	}
}