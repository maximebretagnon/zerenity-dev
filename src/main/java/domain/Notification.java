package domain;

// Generated 9 nov. 2015 19:47:09 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Notification generated by hbm2java
 */
@Entity
@Table(name = "notification", schema = "public")
@XmlRootElement	(name ="notification")
public class Notification implements java.io.Serializable {

	private short notificationId;
	private User user;
	private boolean isRead;
	private boolean isCreationDemand;
	private String message;

	public Notification() {
	}

	public Notification(short notificationId, User user, boolean isRead,
			boolean isCreationDemand, String message) {
		this.notificationId = notificationId;
		this.user = user;
		this.isRead = isRead;
		this.isCreationDemand = isCreationDemand;
		this.message = message;
	}

	@Id
	@Column(name = "notification_id", unique = true, nullable = false)
	@SequenceGenerator(name="notificationIdSeq", sequenceName="public.notification_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificationIdSeq")
	public short getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(short notificationId) {
		this.notificationId = notificationId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver", nullable = false)
	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "is_read", nullable = false)
	public boolean isIsRead() {
		return this.isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Column(name = "is_creation_demand", nullable = false)
	public boolean isIsCreationDemand() {
		return this.isCreationDemand;
	}

	public void setIsCreationDemand(boolean isCreationDemand) {
		this.isCreationDemand = isCreationDemand;
	}

	@Column(name = "message", nullable = false, length = 255)
	public String getUserMessage() {
		return this.message;
	}

	public void setUserMessage(String message) {
		this.message = message;
	}
}
