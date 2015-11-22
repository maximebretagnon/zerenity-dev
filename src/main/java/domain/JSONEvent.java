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

@XmlRootElement	(name ="JSONevent")
public class JSONEvent implements java.io.Serializable {

	private short repetitionId;
	private short userId;
	private short roomId;
	private short activityId;
	private String eventName;
	private Date eventStartDate;
	private Date eventEndDate;
	private Double eventPrice;

	public JSONEvent() {
	}

	public JSONEvent(short repetitionId, short userId, short roomId, short activityId,
			String eventname, Date eventStartDate, Date eventEndDate, Double eventPrice) {
		this.activityId = activityId;
		this.repetitionId = repetitionId;
		this.userId = userId;
		this.roomId = roomId;
		this.eventName = eventname;
		this.eventEndDate = eventEndDate;
		this.eventStartDate = eventStartDate;
		this.eventPrice = eventPrice;
	}
	
	public JSONEvent(short userId, short roomId, short activityId,
			String eventname, Date eventStartDate, Date eventEndDate, Double eventPrice) {
		this.activityId = activityId;
		this.userId = userId;
		this.roomId = roomId;
		this.eventName = eventname;
		this.eventEndDate = eventEndDate;
		this.eventStartDate = eventStartDate;
		this.eventPrice = eventPrice;
	}

	public short getRepetitionId() {
		return repetitionId;
	}

	public void setRepetitionId(short repetitionId) {
		this.repetitionId = repetitionId;
	}

	public short getUserId() {
		return userId;
	}

	public void setUserId(short userId) {
		this.userId = userId;
	}

	public short getRoomId() {
		return roomId;
	}

	public void setRoomId(short roomId) {
		this.roomId = roomId;
	}

	public short getActivityId() {
		return activityId;
	}

	public void setActivityId(short activityId) {
		this.activityId = activityId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Double getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Double eventPrice) {
		this.eventPrice = eventPrice;
	}

}
