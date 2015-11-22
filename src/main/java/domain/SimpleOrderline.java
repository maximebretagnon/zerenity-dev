package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement	(name ="orderlines")
public class SimpleOrderline implements java.io.Serializable {

	private short productId;
	private short quantity;

	public SimpleOrderline() {
	}

	public SimpleOrderline(short productId, short quantity) {
		this.setProductId(productId);
		this.setQuantity(quantity);
	}

	public short getProductId() {
		return productId;
	}

	public void setProductId(short productId) {
		this.productId = productId;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

}
