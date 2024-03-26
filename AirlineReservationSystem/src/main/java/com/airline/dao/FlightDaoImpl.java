package com.airline.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.airline.entities.Flights;


public class FlightDaoImpl implements FlightDao {
	
private Session session;
	
	public FlightDaoImpl(Session session) {
		super();
		this.session = session;
	}

    public Flights getFlightById(int flightid) {
        return session.get(Flights.class, flightid);
    }
    
    public void addFlight(Flights fobj) {
    	
    	System.out.println(fobj);
    	try {
    	  Transaction tx=session.beginTransaction();
		  session.save(fobj);
	      tx.commit();
	      
	      System.out.println(fobj);
	      System.out.println("Record inserted into flights table");		
        } catch (Exception e) {
        	e.printStackTrace();
            }
            
        }
    


    public void updateFlight(int flightid) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(flightid);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

	public List<Flights> getAllFlights() {

		 Transaction tx=session.beginTransaction();
		 Query qobj=session.createQuery("select fobj from Flights fobj" );
		
		 List<Flights> list=(List<Flights>)qobj.getResultList();
		 tx.commit();
		 return list;
	
		
	}

	@Override
	public void deleteFlight(int flightid) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getFlights(Flights selectedflight) {
		// TODO Auto-generated method stub
		
	}

}
	

	
		/*private Session session;
		
		public FlightDaoImpl(Session session) {
			super();
			this.session = session;
		}
		
		

		public void insert(Flights fobj){
			
		  try {
			  
			  Transaction tx=session.beginTransaction();
			  session.save(fobj);
		      tx.commit();
		      
		      System.out.println(fobj);
		      System.out.println("Record inserted into flights table");		
		  }catch(Exception e) {
			  e.printStackTrace();
			  }
		}
		
		public void update(int flightid, String flightname, LocalTime departuretime, LocalTime Arrivaltime, int capacity, int price){
		     
		    try {
		    	
		    	
				Flights fobj=session.find(Flights.class,flightid);
		        if(fobj==null)
		        	System.out.println("Record not found");
		        else {
		        	//aobj.setAdminid(adminid);
		        	fobj.setFlightName(flightname);
		        	fobj.setArrivaltime(Arrivaltime);
		        	fobj.setDeparturetime(departuretime);
		        	fobj.setCapacity(capacity);
		        	fobj.setPrice(price);
		        
		        	
		        	Transaction tx=session.beginTransaction();
		        	session.merge(fobj);
		        	tx.commit();
		        	
		        	System.out.println(fobj);
		        	System.out.println("Record updated into flights table");
		        }
		    }catch(Exception e) {
		        	e.printStackTrace();
		        }
		    }
		
		public void search(int flightid) {
			 
			 try {
				 Flights fobj=session.find(Flights.class,flightid);
				 if(fobj==null)
					 System.out.println("Record not found");
				 else {
					 System.out.println(fobj);
				 }
			 }catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		
		 public List<Flights> select()
		 {
			 Transaction tx=session.beginTransaction();
			 Query qobj=session.createQuery("select fobj from Flights fobj" );
			 @SuppressWarnings("unchecked")
			 List<Flights> list=(List<Flights>)qobj.getResultList();
			 tx.commit();
			 return list;
		 }



		@Override
		public void update(int flightid, String flightname, Time departuretime, Time Arrivaltime, int capacity,
				int price) {
			// TODO Auto-generated method stub
			
		}*/








		



