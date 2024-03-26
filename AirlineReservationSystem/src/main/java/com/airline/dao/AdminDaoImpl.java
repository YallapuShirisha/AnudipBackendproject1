package com.airline.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airline.entities.Admin;


public  class AdminDaoImpl implements AdminDao {
	
	
	private final Session session;

    public AdminDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public Admin getAdminById(int adminid) {
        return session.get(Admin.class, adminid);
    }

    
    @Override
    public void addAdmin(Admin aobj) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(aobj);
            transaction.commit();
            System.out.println("Record inserted into Admin table");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /*@Override
    public Admin getLoginByAdminEmailAndPassword(String admin_email, String password) {
        try {
            return session.createQuery("FROM Admin WHERE admin_email = :admin_email AND password = :password", Admin.class)
                    .setParameter("admin_email", admin_email)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error retrieving login details: " + e.getMessage());
            return null;
        }
    }*/
    @Override
    public void updateAdmin(int adminid) {
    
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(adminid);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public List<Admin> select() {
		Transaction tx=session.beginTransaction();
		 @SuppressWarnings("rawtypes")
		Query qobj=session.createQuery("select aobj from Admin aobj" );
		 @SuppressWarnings("unchecked")
		 List<Admin> list=(List<Admin>)qobj.getResultList();
		 tx.commit();
		 return list;
	}

	
   
}


	
	/*private Session session;
	private Object adminid;
	
	

	public AdminDaoImpl(Session session) {
		super();
		this.session = session;
	}

	public void insert(Admin aobj){
		
	  try {
		  
		  Transaction tx=session.beginTransaction();
		  session.save(aobj);
	      tx.commit();
	      
	      System.out.println(aobj);
	      System.out.println("Record inserted into Course table");		
	  }catch(Exception e) {
		  e.printStackTrace();
		  }
	}
	
	public void update(int Adminid,Registeration robj){
	     
	    try {
	    	
	    	
			Admin aobj=session.find(Admin.class,adminid);
	        if(aobj==null)
	        	System.out.println("Record not found");
	        else {
	        	//aobj.setAdminid(adminid);
	        	aobj.setRobj(robj);
	        	
	        	Transaction tx=session.beginTransaction();
	        	session.merge(aobj);
	        	tx.commit();
	        	
	        	System.out.println(aobj);
	        	System.out.println("Record updated into department table");
	        }
	    }catch(Exception e) {
	        	e.printStackTrace();
	        }
	    }
	
	 public List<Admin> select()
	 {
		 Transaction tx=session.beginTransaction();
		 Query qobj=session.createQuery("select aobj from Admin aobj" );
		 @SuppressWarnings("unchecked")
		 List<Admin> list=(List<Admin>)qobj.getResultList();
		 tx.commit();
		 return list;
	 }

	@Override
	public void update(int adminid, int registerationid) {
		// TODO Auto-generated method stub
		
	}*/

	


