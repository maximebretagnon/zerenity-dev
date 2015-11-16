package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ProductCategory generated by hbm2java
 */
@Entity
@Table(name = "ProductCategory", schema = "public")
@XmlRootElement	(name ="category")
public class ProductCategory implements java.io.Serializable {

	private short categoryId;
	private ProductCategory ProductCategory;
	private String categoryLabel;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<ProductCategory> ProductCategorys = new HashSet<ProductCategory>(
			0);

	public ProductCategory() {
	}

	public ProductCategory(short categoryId, ProductCategory ProductCategory) {
		this.categoryId = categoryId;
		this.ProductCategory = ProductCategory;
	}

	public ProductCategory(short categoryId, ProductCategory ProductCategory,
			String categoryLabel, Set<Product> products,
			Set<ProductCategory> ProductCategorys) {
		this.categoryId = categoryId;
		this.ProductCategory = ProductCategory;
		this.categoryLabel = categoryLabel;
		this.products = products;
		this.ProductCategorys = ProductCategorys;
	}

	@Id
	@Column(name = "category_id", unique = true, nullable = false)
	@SequenceGenerator(name="catProdIdSeq", sequenceName="public.category_product_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="catProdIdSeq")
	public short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_parent_id", nullable = false)
	@XmlTransient
	public ProductCategory getProductCategory() {
		return this.ProductCategory;
	}

	public void setProductCategory(ProductCategory ProductCategory) {
		this.ProductCategory = ProductCategory;
	}

	@Column(name = "category_label", length = 50)
	public String getCategoryLabel() {
		return this.categoryLabel;
	}

	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ProductCategory")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ProductCategory")
	public Set<ProductCategory> getProductCategorys() {
		return this.ProductCategorys;
	}

	public void setProductCategorys(Set<ProductCategory> ProductCategorys) {
		this.ProductCategorys = ProductCategorys;
	}

}