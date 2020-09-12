package br.com.fiap.aoj.productcategories.data;

import br.com.fiap.aoj.productcategories.domain.CategoryDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<CategoryDomain, String> { }