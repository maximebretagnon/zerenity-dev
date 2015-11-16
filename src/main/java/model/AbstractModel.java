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
    
    public List<T> query(String hsql, Map<String, Object> params) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

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
        HibernateUtil.closeSession();
        return result;
    }
    
    public List<T> findAll() {
        return query("from "+cl.getName(), null);
    }
    
    public T get(PK id) {
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        T element = (T) session.get(cl, (Serializable) id);

        tx.commit();
        HibernateUtil.closeSession();
        return element;
    }
    
    public void update(T elt) {
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.update(elt);
        tx.commit();
        HibernateUtil.closeSession();
    }

    public void delete(T elt) {
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.delete(elt);
        tx.commit();
        HibernateUtil.closeSession();
    }
    
    public void deleteAll() {
        query("delete from "+cl.getName(),null);

    }
    
    public PK save(T elt) {
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        PK result=(PK)session.save(elt);
        tx.commit();
        HibernateUtil.closeSession();
        return result;
    }
}
