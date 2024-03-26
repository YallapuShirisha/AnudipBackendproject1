package com.airline;

import java.sql.Date;


import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.airline.crud.FlightCrud;
import com.airline.dao.AdminDao;
import com.airline.entities.Passenger;
import com.airline.dao.AdminDaoImpl;
import com.airline.dao.BookingDao;
import com.airline.dao.BookingDaoImpl;
import com.airline.dao.PassengerDao;
import com.airline.dao.PassengerDaoImpl;
import com.airline.dao.PaymentDao;
import com.airline.dao.PaymentDaoImpl;
import com.airline.dao.RegisterationDao;
import com.airline.dao.RegisterationDaoImpl;
import com.airline.dao.FlightDao;
import com.airline.dao.FlightDaoImpl;
import com.airline.entities.Admin;
import com.airline.entities.Bookings;
import com.airline.entities.Payments;
import com.airline.entities.Registeration;

import com.airline.entities.Flights;


public class Menu {
	//private static final BookingDao BookingsDao = null;
	//org.hibernate.SessionFactory factory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	//org.hibernate.Session session = factory.openSession();
	
	
	Scanner scanner=new Scanner(System.in);
	Session session;	
	
	PassengerDao passengerDao ;
	AdminDao adminDao; 
	FlightDao flightDao;
	BookingDao bookingdao;
	PaymentDao paymentDao;
	
	RegisterationDao registerationDao;
	
    Menu(Session session){
      this.session=session;
       registerationDao=new RegisterationDaoImpl(session);
       passengerDao = new PassengerDaoImpl(session);
    adminDao = new AdminDaoImpl(session);
   	 flightDao = new FlightDaoImpl(session);
   	 bookingdao = new BookingDaoImpl(session);
   	 paymentDao = new PaymentDaoImpl(session);
   	 
    }
    
	private String arrivaltime;
	

	public void createRegisteration() {
		
	        try {
	            System.out.println("***** Registration *****");
	            
	            
	            System.out.print("Enter user name: ");
	            String username = scanner.next();
	            scanner.nextLine();
	            System.out.println("Enter email: ");
	            String email = scanner.next();
	            System.out.println("Enter your password: ");
	            String password = scanner.next();
	            System.out.println("Enter your address: ");
	            String address= scanner.next();
	            System.out.println("Enter your contact: ");
	            int contact = scanner.nextInt();
	            System.out.println("Enter your Gender: ");
	            String gender = scanner.next();
	            System.out.println("Enter your age: ");
	            String age = scanner.next();
	            
	            
                Registeration robj = new Registeration();
               //newReg.setRegisterationid(registerationid);
                robj.setUsername(username);
                robj.setPassword(password);
                robj.setEmail(email);
                robj.setAddress(address);
                robj.setAge(age);
                robj.setContactno(contact);
                robj.setGender(gender);
                /*Admin aobj = new Admin();
                robj.setAobj(aobj);
                //aobj.setRobj(newReg);
                System.out.println(robj);
                System.out.println(aobj);*/

                registerationDao.addRegisteration(robj);
	            
	            System.out.println(" Registration successful!");
	        

        } catch (Exception e) {
            System.out.println(" Registration failed. Please try again.");
            e.printStackTrace();
        }
	        
	        
    }


	public void adminLogin() {
        System.out.println("*****  LOGIN *****");
        /*System.out.println("Enter admin id:");
        int adminid = scanner.nextInt();
        System.out.println("Enter admin email:");
        String admin_email = scanner.next();
        System.out.println("Enter admin Password:");
        String password = scanner.next();
        */
        System.out.println("Enter registeration id to find:");
        int registerationId=scanner.nextInt();

        Registeration aobj=session.find(Registeration.class,registerationId);
        if(aobj==null)
        	System.out.println("Record not found");
        else {
        	System.out.println("reg id:"+aobj.getRegisterationid());
        	
        }
        Admin robj = new Admin();
        
        robj.setRobj(aobj);
        
        adminDao.addAdmin(robj);

       
        
        if(aobj==null) {
        	System.out.println("login failed.try again");
        }else
        {
        	System.out.println("Admin login successfull");
        }
        	
        	
        
        
        
        
	}       	

	
	
	public void bookFlight() {
		
		
		List<Flights> availableFlights = flightDao.getAllFlights(); // Initialize availableFlights directly
		if (availableFlights == null || availableFlights.isEmpty()) {
		    System.out.println("No available flights to book.");
		    return;
		}

		// Display available flights
		System.out.println("Available Flights:");
		for (Flights flight : availableFlights) {
		    System.out.println(flight.getFlightid() + " - " + flight.getBooking_flight_type());
		}

				
				
		System.out.println(" Booking details");
		
		
		System.out.print("Enter flight id:");
		int flightid=scanner.nextInt();
		
		System.out.print("Booking flight type: ");
		scanner.nextLine();
		String booking_flight_type = scanner.nextLine();
		System.out.print("Booking date (YYYY-MM-DD): ");
		String bookingdate = scanner.nextLine();
		System.out.print("Booking time (HH:MM:SS): ");
		String bookingtime = scanner.nextLine();
		
	    System.out.print("Number of ticket bookings:");
		int num_of_tickets=scanner.nextInt();
		
		// Create a booking object
		Bookings bobj = new Bookings();
		bobj.setBookingDate(bookingdate);
		bobj.setBookingtime(bookingtime);
		bobj.setBookingFlightType(booking_flight_type);
		bobj.setNumofTickets(num_of_tickets);
		
        //Bookings pobj1 = new Bookings();
        //Passenger bobj1=new Passenger();
		System.out.println("Enter passenger id :");
        int passengerid=scanner.nextInt();
        
        Passenger pobj=new Passenger();
        bobj.setPobj(pobj);
        Flights fobj=new Flights();
        bobj.setFobj(fobj);
      
		bookingdao.addBooking(bobj);
        
        
		
		
		List<Flights> availableSeats = null;
		try {
			availableSeats = flightDao.getAllFlights();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (availableSeats.isEmpty()) {
			System.out.println(" no available Seats to book.");
			
		}else {
			System.out.println("available Seats are there to book.");
		}

		// Ask admin to select a flight
		System.out.print("Enter flight id to book the flight: ");
	    flightid = scanner.nextInt();

		// Check if the selected flight is available
		Flights selectedflight = flightDao.getFlightById(flightid);
		if ( selectedflight == null || !availableFlights.contains( selectedflight)) {
			System.out.println("Invalid flight id or flight not available.");
			return;
		}

		
		// Add the selected flight to the booking
		Bookings bobj1=new Bookings();
		bobj1.getFlight(selectedflight);

		// Save the booking
		//bookingDao.bookFlight(bobj, bookingid);
		

		System.out.println("Booking successful!");
	}

	public void makePayment( int bookingid, String paydate, Double payamount, String paystatus) {
		System.out.println("Enter payment details:");
		System.out.print("Booking ID: ");
		bookingid = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Payment date (YYYY-MM-DD): ");
	    paydate = scanner.nextLine();
		System.out.print("Payment amount: ");
		payamount = scanner.nextDouble();
		System.out.print("Payment status: ");
		scanner.nextLine();
		paystatus = scanner.nextLine();
	

		// Created Payment object
		Payments pobj1 = new Payments();
		pobj1.setPayDate(paydate);
		pobj1.setPayamount(payamount);
		pobj1.setPaystatus(paystatus);

		// Get Booking object by ID
		Bookings bobj = new Bookings();
		if (bobj != null) {
			pobj1.setBobj(bobj);

			// Save payment to database
			paymentDao.addPayment(pobj1);

			System.out.println("Payment successful!");
		} else {
			System.out.println("Booking not found!");
		}
	}

	

	
	/*public  void addFlights() {
		int flightid = 0;
		Menu menu=new Menu();
		//menu.addFlight(flightid);
		
	}*/

	/*private void addFlight(int flightid) {
		String flightname;
		int capacity;
		Time departuretime;
		FlightCrud.addFlight1( flightname, booking_flight_type, capacity, price, arrivaltime,  departuretime,price);
		
		
	}*/

	

	public void viewPayment() {
        System.out.println("Enter payment id:");
		int payid=scanner.nextInt();
		
		Payments pobj2 = session.find(Payments.class,payid);
		if(pobj2!=null) {
			System.out.println("Record  found");
		System.out.println("Payment id:"+pobj2.getPayamount());
		System.out.println("Payment Date:"+pobj2.getPayDate());
		System.out.println("Payment Status:"+pobj2.getPaystatus());
		System.out.println("Booking id:"+pobj2.getBobj());
		}
		else {
			System.out.println("Record not found");
			}
	}

	//@SuppressWarnings("unused")
	
	public void viewBookings() {
		System.out.println("Enter booking id:");
		
		int bookingid=scanner.nextInt();
		Bookings bobj = session.find(Bookings.class,bookingid);
		if(bobj==null)
			System.out.println("Record not found");
		else {
			System.out.println("Booking id:"+bobj.getBookingid());
			System.out.println("booking Date:"+bobj.getBookingDate());
			System.out.println("booking Flight type:"+bobj.getBookingFlightType());
			System.out.println("booking Time:"+bobj.getBookingtime());
			System.out.println("Booking :"+bobj.getNumofTickets());
			
			/*System.out.println("Enter registeration id:");
			int registerationid=scanner.nextInt();*/
			
			 //Registeration robj =new Registeration();
			 //Passenger pobj=new Passenger();
			// pobj.setRobj(robj);
			 
		       /* if (pobj != null) {
		        	System.out.println("Passenger not found");
		        } else {
		            System.out.println("Passenger id: " + pobj.getPassengerid());
		        }*/
		}
		/*System.out.println("Accept customer details and Booking details :");
		int flightid = 0,capacity = 0;
		String flightname = null;
		Time departuretime = null,Arrivaltime=null;
		String booking_flight_type=null;
		double price=0;
		
		System.out.println("booking id"+bookingid+"flight id"+flightid+"flight name"+flightname+"departuretime"+departuretime+"arrival time"+Arrivaltime+"Booking_Flight_type"+booking_flight_type+
				"capacity"+capacity+"price"+price);*/
	
	}
	

	

	/*@SuppressWarnings("unused")
	private void viewPassengerBookings(int bookingid,int flightid,String flightname,Time departuretime,String booking_flight_type,Time arrivaltime,int capacity,double price) {
	    List<Bookings> booking = BookingsDao.getAllBookings();
	    
	    if (booking.isEmpty()) {
	        System.out.println("No bookings found.");
	        return;
	    }

	    System.out.println("Passenger Bookings:");
	    System.out.println("booking id"+bookingid+"booking flight id"+flightid+"\t"+"booking flight name"+flightname+"\t"+"departure time"+departuretime+"booking_flight_type"+booking_flight_type+"\t"+"Arrival Time"+arrivaltime+"\t"+"Total price"+price+"\t"+"capacity"+capacity);
	}
	
	

	@SuppressWarnings("unused")
	private void viewPassengerPayments(int bookingid,int payid,Date paydate, Double payamount, String paystatus) {
	    List<Payments> payment = PaymentDao.getAllPayments();
	    if (payment.isEmpty()) {
	        System.out.println("No bookings found.");
	        return;
	    }

	    System.out.println("booking Payments:");
	    System.out.println("Booking id"+bookingid+"\n"+"payment id"+payid+"\n"+"payment date"+paydate+"\n"+"pay amount"+payamount+"\n"+"payment status"+paystatus);
	    
	}*/
	
	
	//public void PassengerLogin(String paydate, int bookingid, Double payamount, String paystatus ) {
		
		public void passengerLogin() {
			
			
			
			System.out.println("*****  LOGIN *****");
	        /*System.out.println("Enter admin id:");
	        int adminid = scanner.nextInt();
	        System.out.println("Enter admin email:");
	        String admin_email = scanner.next();
	        System.out.println("Enter admin Password:");
	        String password = scanner.next();
	        */
	        System.out.println("Enter registeration id to find:");
	        int registerationId=scanner.nextInt();

	        Registeration pobj=session.find(Registeration.class,registerationId);
	        if(pobj==null)
	        	System.out.println("Record not found");
	        else {
	        	System.out.println("Reg id:"+pobj.getRegisterationid());
	        	
	        }
	       Passenger robj = new Passenger();
	        
	        robj.setRobj(pobj);
	        
	        passengerDao.addPassenger(robj);

	       
	        
	        if(pobj==null) {
	        	System.out.println("login failed.try again");
	        }else
	        {
	        	System.out.println("Passenger login successfull");
	        }
	        	
	        	
		}
}
	        
		
			//Passenger pobj, Registeration robj, String paydate, int bookingid, Double payamount, Bookings bobj

			/*int choice;
			String email, password;

			do {

				System.out.println("\n\n 1. Sign In");
				System.out.println("2. Forgot Password");
				System.out.println("3. Change Password");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				switch (choice) {

				case 1:
					System.out.print("Enter your email: ");
					email = scanner.next();
					System.out.print("Enter your password: ");
					password = scanner.next();
					System.out.println("You have successfully signed in.");
					System.out.print("Enter your email: ");
					String email1 = scanner.next();
					System.out.print("Enter your password: ");
					String password1 = scanner.next();
					if(email1.equals(email) && password1.equals(password)) {
						System.out.println("password and email matching");	
					}
					else {
						System.out.println("You have entered wrong email id and password");	
					}
					break;

				case 2:
					System.out.print("Enter your email: ");
					email = scanner.next();
					System.out.println("Password reset link has been sent to your email.");
					break;
				case 3:
					System.out.print("Enter your email: ");
					email = scanner.next();
					System.out.print("Enter your old password: ");
					password = scanner.next();
					System.out.print("Enter your new password: ");
					@SuppressWarnings("unused")
					String newPassword = scanner.next();
					System.out.println("Password changed successfully.");
					break;
				case 0:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} while (choice != 0);
			 scanner.close();
		}*/

		/*int ch;
		String payDate = null;
		int bookingId = 0;
	    String paystatus = null;
	    
		do {
			System.out.println();
			System.out.println("==================[ Welcome to Passenger MENU ]======================");
			System.out.println();
			System.out.println("1) display All flights \n 2) book a flight  \n 3) make payment \n 4)Back to main menu");
			System.out.print("Enter your choice: ");
			ch = scanner.nextInt();
			scanner.nextLine();

			switch (ch) {
			case 1:
				// Implement view all flights method
				System.out.println("See all flight Details");
				FlightCrud.displayAllFlights();
				System.out.println("Note: note down the flightid to book flight");
				break;

			case 2:
				// to book the flight
				System.out.println("Accept customer details and Booking details :");
				bookFlight();
				bookFlight1( pobj,robj,bobj, bookingid );
			
				break;

			case 4:
				//implement a payment method
				makePayment(payDate,  bookingId,  payamount, paystatus);
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		} while (ch!=5);
	}*/

	

		
		
	


	

	
	
	

	

	

	

	


	


/*import java.util.Scanner;

import org.hibernate.cfg.Configuration;

import com.airline.entities.Admin;
import com.airline.entities.Passenger;
import com.airline.entities.Registeration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Menu{

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = factory.openSession();
	Scanner sc = new Scanner(System.in);

	@SuppressWarnings("unused")
	public void adminLogin() {
		System.out.println("************** Admin Login ************");

		System.out.println("Enter Admin details");
		System.out.println("Enter admin id:");
		int id=sc.nextInt();

		Admin newAdmin=new Admin();
		if(newAdmin!=null) {
			System.out.println("Admin Logedin successfully!");
		} else {
			System.out.println("Admin login failed. Please try again.");
		}

	}
	@SuppressWarnings("unused")
	public void registerationProcess(){


		System.out.println("************** Registeration Process ************");

		System.out.println("Enter username:");
		sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		System.out.println("Enter adress:");
		String address=sc.nextLine();
		System.out.println("Enter email:");
		String email=sc.nextLine();
		System.out.println("Enter contact number:");
		int contactno=sc.nextInt();
		System.out.println("Enter gender:");
		String gender=sc.nextLine();
		System.out.println("Enter age:");
		int age=sc.nextInt();

		Registeration newRegisteration=new Registeration();
		Menu m=new Menu();
		m.createPassenger( name,password,address,email,gender,contactno,age);
		if(newRegisteration!=null) {
			System.out.println("Passenger Registeration successfully!");
		} else {
			System.out.println("Passenger Registeration failed. Please try again.");
		}

	}

	@SuppressWarnings("unused")
	public void createPassenger(String name,String password,String address,String email,String gender,int contactno,int age ) {

		System.out.println("1) PassengerAccount creation \n2)show passenger details");
		System.out.println("Enter Your Choice:");
		int ch = sc.nextInt();
		switch(ch){
		case 1:
			System.out.println("************** Create Passenger Account ************");
			System.out.println("Enter passenger id:");
			int id=sc.nextInt();
			Passenger newPassenger=new Passenger();
			if(newPassenger!=null) {
				System.out.println("Passenger Account created successfully!");
			} else {
				System.out.println("Passenger account creation failed. Please try again.");
			}
			break;
		case 2:
			System.out.println("************** Passenger Details ************");
			System.out.println("Passenger name:"+name+"\n"+"password:"+password+"\n"+"address:"+address+"\n"+"email:"+email+"\n"+"gender:"+gender+"\n"+
					"contact number:"+contactno+"\n"+"age:"+age);
			break;

		default:
			System.out.println("invalid output!");
		}
	}

	public void passengerLogin1() {
		
	

		int choice;
		String email, password;

		do {

			System.out.println("\n\n 1. Sign In");
			System.out.println("2. Forgot Password");
			System.out.println("3. Change Password");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch (choice) {

			case 1:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.print("Enter your password: ");
				password = sc.next();
				System.out.println("You have successfully signed in.");
				System.out.print("Enter your email: ");
				String email1 = sc.next();
				System.out.print("Enter your password: ");
				String password1 = sc.next();
				if(email1.equals(email) && password1.equals(password)) {
					System.out.println("password and email matching");	
				}
				else {
					System.out.println("You have entered wrong email id and password");	
				}
				break;

			case 2:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.println("Password reset link has been sent to your email.");
				break;
			case 3:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.print("Enter your old password: ");
				password = sc.next();
				System.out.print("Enter your new password: ");
				String newPassword = sc.next();
				System.out.println("Password changed successfully.");
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);

		sc.close();

	}


	public void passengerLogin() {


		int choice;
		String email, password;

		do {

			System.out.println("\n\n 1. Sign In");
			System.out.println("2. Forgot Password");
			System.out.println("3. Change Password");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch (choice) {

			case 1:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.print("Enter your password: ");
				password = sc.next();
				System.out.println("You have successfully signed in.");
				System.out.print("Enter your email: ");
				String email1 = sc.next();
				System.out.print("Enter your password: ");
				String password1 = sc.next();
				if(email1.equals(email) && password1.equals(password)) {
					System.out.println("password and email matching");	
				}
				else {
					System.out.println("You have entered wrong email id and password");	
				}
				break;

			case 2:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.println("Password reset link has been sent to your email.");
				break;
			case 3:
				System.out.print("Enter your email: ");
				email = sc.next();
				System.out.print("Enter your old password: ");
				password = sc.next();
				System.out.print("Enter your new password: ");
				String newPassword = sc.next();
				System.out.println("Password changed successfully.");
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);
		 sc.close();
}
		
	public void passengerBooking() {
	
	        int choice;
	        do {
	            System.out.println();
	            System.out.println("==================[ Welcome to Customer MENU ]======================");
	            System.out.println();
	            System.out.println("1) View Flights \n2) Book a Flight  \n3) Check booking Status   \n4) Log Out");
	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();

	            switch (choice) {
	            case 1:
	            	System.out.println("See all Flight Details");
	            	
                	FlightCrud.displayallFlights();
                    System.out.println("Note: note down the Flight ID to reserve a flight");
                    break;
                    
	            case 2:
	            	
	            case 3:
	            	System.out.print("Enter flight ID to see booking for a particular car: ");
                    int fID = sc.nextInt();
	            	
	            	fobj.Flights(flightID);
	            	
	            case 4:
	            	break;
	            	

	            }while(choice!=4)


		sc.close();

	}

}*/






