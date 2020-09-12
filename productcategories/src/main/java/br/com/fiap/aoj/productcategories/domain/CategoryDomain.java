package br.com.fiap.aoj.productcategories.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class CategoryDomain implements Serializable {

	private static final long serialVersionUID = 1649219665299117651L;

	@MongoId
	private String name;

	private Set<ProductId> products;

	private CategoryDomain(final Builder builder){
		this.name = builder.tag;
		this.products = builder.products;
	}

	//Construtor padrão para serialização do mongo
	public CategoryDomain(){}

	public String getName() {
		return name;
	}

	public Set<ProductId> getProducts() {
		return products;
	}

	public void add(final ProductId productId) {
		products.add(productId);
	}

	public Integer productsCount(){
		return products.size();
	}

	public static final Name builder(){
		return new Builder();
	}

	public static final class Builder implements Name, Build{
		private String tag;
		private Set<ProductId> products= Collections.emptySet();

		@Override
		public Build name(final String tag) {
			this.tag = tag;
			return this;
		}

		@Override
		public Build product(final ProductId productId) {
			products.add(productId);
			return this;
		}

		@Override
		public Build products(final Set<ProductId> productsDomain) {
			this.products = productsDomain;
			return this;
		}

		@Override
		public CategoryDomain builder() {
			return new CategoryDomain(this);
		}
	}

	public interface Name {
		public Build name(final String name);
	}

	public interface Build{
		public Build product(final ProductId productId);
		public Build products(final Set<ProductId> productsDomain);
		public CategoryDomain builder();
	}
}
