package domain;

// Generated 4 nov. 2015 13:38:10 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Inscription generated by hbm2java
 */
@Entity
@Table(name = "inscription", schema = "public")
public class Inscription implements java.io.Serializable {

	private InscriptionId id;
	private ZerenetyUser zerenetyUser;
	private Event event;
	private Date inscriptionDate;

	public Inscription() {
	}

	public Inscription(InscriptionId id, ZerenetyUser zerenetyUser,
			Event event, Date inscriptionDate) {
		this.id = id;
		this.zerenetyUser = zerenetyUser;
		this.event = event;
		this.inscriptionDate = inscriptionDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "memberId", column = @Column(name = "member_id", nullable = false)),
			@AttributeOverride(name = "eventId", column = @Column(name = "event_id", nullable = false)) })
	public InscriptionId getId() {
		return this.id;
	}

	public void setId(InscriptionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
	public ZerenetyUser getZerenetyUser() {
		return this.zerenetyUser;
	}

	public void setZerenetyUser(ZerenetyUser zerenetyUser) {
		this.zerenetyUser = zerenetyUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "inscription_date", nullable = false, length = 13)
	public Date getInscriptionDate() {
		return this.inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

}
