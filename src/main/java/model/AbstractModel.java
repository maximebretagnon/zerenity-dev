package model;

import persistence.HibernateUtil;

import java.io.Serializable;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AbstractModel<T, PK> {

    private Class<T> cl;

    public AbstractModel() {
    }
    
    public AbstractModel(Class<T> cl) {
        this.cl=cl;
    }
    
    public List<T> query(String hsql, Map<String, Object> params) throws Exception {
    	Session session = HibernateUtil.currentSession();
    	try {
	        Transaction tx = session.beginTransaction();
	        try{
		        Query query = session.createQuery(hsql);
		        if (params != null) {
		            for (String i : params.keySet()) {
		                query.setParameter(i, params.get(i));
		            }
		        }
		
		        List<T> result = null;
		        if ((hsql.toUpperCase().indexOf("DELETE") == -1)
		                && (hsql.toUpperCase().indexOf("UPDATE") == -1)
		                && (hsql.toUpperCase().indexOf("INSERT") == -1)) {
		            result = query.list();
		        } else {
		            query.executeUpdate();
		        }
		        tx.commit();
		        return result;
	        } catch(Exception ex) {
	        	tx.rollback();
	        	throw ex;
	        } 
    	}finally {
        	HibernateUtil.closeSession();
        }
    }
    
    public List<T> findAll() throws Exception {
        return query("from "+cl.getName(), null);
    }
    
    public T get(PK id) throws Exception {
    	Session session = HibernateUtil.currentSession();
    	try{
	        Transaction tx = session.beginTransaction();
	        try {
		        T element = (T) session.get(cl, (Serializable) id);
		
		        tx.commit();
		        return element;
	        }catch (Exception ex){
	        	tx.rollback();
	        	throw ex;
	        }
    	}finally {
    		HibernateUtil.closeSession();
    	}
    }
    
    public void update(T elt) throws Exception {
    	Session session = HibernateUtil.currentSession();
    	try {
	        Transaction tx = session.beginTransaction();
	        try {
		        session.update(elt);
		        tx.commit();       
	        }catch(Exception ex){
	        	tx.rollback();
	        	throw(ex);
	        }
    	}finally{
    		HibernateUtil.closeSession();
    	}
    }

    public void delete(T elt) throws Exception {
    	Session session = HibernateUtil.currentSession();
    	try {
	        Transaction tx = session.beginTransaction();
	        try {
	        	session.delete(elt);
		        tx.commit();       
	        }catch(Exception ex){
	        	tx.rollback();
	        	throw(ex);
	        }
    	}finally{
    		HibernateUtil.closeSession();
    	}
    }
    
    public void deleteAll() throws Exception {
        query("delete from "+cl.getName(),null);

    }
    
    public PK save(T elt) throws Exception {
    	Session session = HibernateUtil.currentSession();
    	try {
	        Transaction tx = session.beginTransaction();
	        try {
	        	PK result=(PK)session.save(elt);
		        tx.commit();  
		        return result;
	        }catch(Exception ex){
	        	tx.rollback();
	        	throw(ex);
	        }
    	}finally{
    		HibernateUtil.closeSession();
    	}
    }
}
