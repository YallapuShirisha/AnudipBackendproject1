package com.airline.dao;

import java.sql.Time;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.airline.entities.Bookings;



public  class BookingDaoImpl implements BookingDao {
	
	private Session session = null;

    public BookingDaoImpl(Session session) {
        this.session = session;
    }

    public Bookings getBookingById(int bookingid) {
        return session.get(Bookings.class, bookingid);
    }

    @Override
    public void addBooking(Bookings bobj) {
    	System.out.println(bobj);
		
  	  try {
  		  
  		  Transaction tx=session.beginTransaction();
  		  session.save(bobj);
  	      tx.commit();
  	      
  	      System.out.println(bobj);
  	      System.out.println("Record inserted into Booking table");		
  	  }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBooking(int bookingid) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(bookingid);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(int bookingId) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Bookings booking = session.get(Bookings.class, bookingId);
            if (booking != null) {
                session.delete(booking);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public List<Bookings> getAllBookings() {

		 Transaction tx=session.beginTransaction();
		 Query qobj=session.createQuery("select aobj from Bookings aobj" );
		 @SuppressWarnings("unchecked")
		 List<Bookings> list=(List<Bookings>)qobj.getResultList();
		 tx.commit();
		 return list;
	
		
	}

	@Override
	public void bookFlight(int bookingid, int numofTickets, int totalprice, String bookingDate, Time bookingtime,
			String status) {
		
		
	}

	@Override
	public void bookFlight(Bookings bobj, int bookingid) {
		// TODO Auto-generated method stub
		
	}

	
}
	
	/*private Session session;
	public BookingDaoImpl(Session session) {
		super();
		this.session = session;
	}
	

	public void insert(Bookings bobj){
		
	  try {
		  
		  Transaction tx=session.beginTransaction();
		  session.save(bobj);
	      tx.commit();
	      
	      System.out.println(bobj);
	      System.out.println("Record inserted into Course table");		
	  }catch(Exception e) {
		  e.printStackTrace();
		  }
	}
	
	public void update(int bookingid, int numofTickets, int totalprice, String bookingDate, Time bookingtime,String status,List<Flights> fobj,Passenger pobj){
	     
	    try {
	    	
	    	
			Bookings bobj=session.find(Bookings.class,bookingid);
	        if(bobj==null)
	        	System.out.println("Record not found");
	        else {
	        	//aobj.setAdminid(adminid);
	        	bobj.setNumofTickets(numofTickets);
	        	bobj.setBookingDate(bookingDate);
	        	bobj.setBookingtime(bookingtime);
	        	bobj.setStatus( status);
	        	bobj.setTotalprice(totalprice);
	        	bobj.setPobj2(pobj);
	        	bobj.setFobj(fobj);
	        	
	        	
	        	Transaction tx=session.beginTransaction();
	        	session.merge(bobj);
	        	tx.commit();
	        	
	        	System.out.println(bobj);
	        	System.out.println("Record updated into department table");
	        }
	    }catch(Exception e) {
	        	e.printStackTrace();
	        }
	    }
	
	 public List<Bookings> select()
	 {
		 Transaction tx=session.beginTransaction();
		 Query qobj=session.createQuery("select bobj from Bookings bobj" );
		 @SuppressWarnings("unchecked")
		 List<Bookings> list=(List<Bookings>)qobj.getResultList();
		 tx.commit();
		 return list;
	 }


	@Override
	public void update(int bookingid, int numofTickets, int totalprice, String bookingDate, Time bookingtime,
			String status) {
		// TODO Auto-generated method stub
		
	}



}*/