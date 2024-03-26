package com.airline.entities;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Flights {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int flightid;
	private String flightname;
	private String departuretime;
	private String booking_flight_type;
	private String Arrivaltime;
	private int availableseats;
	private double price;
	private String arrivalplace;
	private String departureplace;
	private String date;
	

	
	
	/*@OneToMany(cascade=CascadeType.ALL,mappedBy="pobj")
	private Bookings bobj;
	
	
	public Bookings getBobj() {
		return bobj;
	}
	public void setBobj(Bookings bobj) {
		this.bobj = bobj;
	}*/
	/*public Flights(int flightid,String flightname, String booking_flight_type, int capacity, Double price, String arrivalplace, String departureplace, String arrivaltime, String departuretime, String departuretime) {
		super();
		// TODO Auto-generated constructor stub
	}*/
	
	public Flights() {
		super();
		
	}
	
	
	public String getBooking_flight_type() {
		return booking_flight_type;
	}
	
	public void setBooking_flight_type(String booking_flight_type) {
		this.booking_flight_type = booking_flight_type;
	}
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public String getFlightName() {
		return flightname;
	}
	public void setFlightName(String flightname) {
		this.flightname = flightname;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}
	public String getArrivaltime() {
		return Arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		Arrivaltime = arrivaltime;
	}
	
	public int getAvailableseats() {
		return availableseats;
	}


	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}


	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getArrivalplace() {
		return arrivalplace;
	}
	public void setArrivalplace(String arrivalplace) {
		this.arrivalplace = arrivalplace;
	}
	public String getDepartureplace() {
		return departureplace;
	}
	public void setDepartureplace(String departureplace) {
		this.departureplace = departureplace;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Flights [flightid=" + flightid + ", flightname=" + flightname + ", departuretime=" + departuretime
				+ ", booking_flight_type=" + booking_flight_type + ", Arrivaltime=" + Arrivaltime + ", availableseats="
				+ availableseats + ", price=" + price + ", arrivalplace=" + arrivalplace + ", departureplace="
				+ departureplace + ", date=" + date + "]";
	}
	
	
	
	
	
}
