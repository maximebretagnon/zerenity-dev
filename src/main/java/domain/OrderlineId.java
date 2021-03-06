package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderlineId generated by hbm2java
 */
@Embeddable
public class OrderlineId implements java.io.Serializable {

	private short productId;
	private short orderId;

	public OrderlineId() {
	}

	public OrderlineId(short productId, short orderId) {
		this.productId = productId;
		this.orderId = orderId;
	}

	@Column(name = "product_id", nullable = false)
	public short getProductId() {
		return this.productId;
	}

	public void setProductId(short productId) {
		this.productId = productId;
	}

	@Column(name = "order_id", nullable = false)
	public short getOrderId() {
		return this.orderId;
	}

	public void setOrderId(short orderId) {
		this.orderId = orderId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderlineId))
			return false;
		OrderlineId castOther = (OrderlineId) other;

		return (this.getProductId() == castOther.getProductId())
				&& (this.getOrderId() == castOther.getOrderId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProductId();
		result = 37 * result + this.getOrderId();
		return result;
	}

}
