package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Inscription generated by hbm2java
 */
@Entity
@Table(name = "inscription", schema = "public")
@XmlRootElement	(name ="inscription")
public class Inscription implements java.io.Serializable {

	private InscriptionId id;
	private User user;
	private Event event;
	private Date inscriptionDate;

	public Inscription() {
	}

	public Inscription(InscriptionId id, User user, Event event,
			Date inscriptionDate) {
		this.id = id;
		this.user = user;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
	@XmlTransient
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
