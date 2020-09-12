package br.com.fiap.aoj.productcategories.domain;


import java.io.Serializable;
import java.util.Objects;

public class ProductId implements Serializable {

	private static final long serialVersionUID = 9015380026072395472L;

	private String productId;

	private ProductId(final String productId){
		this.productId = productId;
	}

	//Construtor padrão para serialização do mongo
	public ProductId(){}

	public String getProductId() {
		return productId;
	}

	public static final ProductId of(final String productId){
		return new ProductId(productId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		br.com.fiap.aoj.productcategories.domain.ProductId that = (br.com.fiap.aoj.productcategories.domain.ProductId) o;
		return Objects.equals(productId, that.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}
}