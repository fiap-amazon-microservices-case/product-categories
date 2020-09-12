package br.com.fiap.aoj.productcategories.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class CategoryDto {

	@NotNull(message = "Campo obrigatório")
	@Size(min = 2, max = 64, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String name;

	@JsonProperty(access = READ_ONLY)
	private Integer productCount;

	public String getName() {
		return name;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
}
