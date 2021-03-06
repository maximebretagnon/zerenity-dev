package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Userorder generated by hbm2java
 */
@Entity
@Table(name = "userorder", schema = "public")
@XmlRootElement	(name ="order")
public class Userorder implements java.io.Serializable {

	private short orderId;
	private User user;
	private Date orderDate;
	private Set<Orderline> orderlines = new HashSet<Orderline>(0);

	public Userorder() {
	}

	public Userorder(short orderId, User user, Date orderDate) {
		this.orderId = orderId;
		this.user = user;
		this.orderDate = orderDate;
	}

	public Userorder(short orderId, User user, Date orderDate,
			Set<Orderline> orderlines) {
		this.orderId = orderId;
		this.user = user;
		this.orderDate = orderDate;
		this.orderlines = orderlines;
	}

	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	@SequenceGenerator(name="orderIdSeq", sequenceName="public.order_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orderIdSeq")
	public short getOrderId() {
		return this.orderId;
	}

	public void setOrderId(short orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date", nullable = false, length = 13)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userorder")
	public Set<Orderline> getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(Set<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

}
