package it.objectmethod.domain;

public class Article {
	private Integer id;
	private Integer cartArticleId;
	private Integer avaiability;
	private Integer quantity;
	private String code;
	private String name;
	private Integer price;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setCartArticleId(Integer cartArticleId) {
		this.cartArticleId = cartArticleId;
	}

	public Integer getCartArticleId() {
		return cartArticleId;
	}

	public void setAvaiability(Integer avaiability) {
		this.avaiability = avaiability;
	}

	public Integer getAvaiability() {
		return avaiability;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getquantity() {
		return quantity;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}
}
