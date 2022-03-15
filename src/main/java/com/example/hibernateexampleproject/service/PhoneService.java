package com.example.hibernateexampleproject.service;

import com.example.hibernateexampleproject.model.Phone;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class PhoneService {

    SessionFactory sessionFactory;

    PhoneService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Phone createPhone(Phone phone){
        sessionFactory.getCurrentSession().persist(phone);
        return phone;
    }
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//    public Phone getPhoneById(Long id){
//        return sessionFactory.getCurrentSession()
//                .createQuery("")
//                setPara
//    }
    @Transactional
    public List<Phone> getAllPhone(){
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Phone",Phone.class)
                .getResultList();
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Phone getByIdPhone(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Phone p WHERE p.id = :id", Phone.class)
                .setParameter("id", id)
                .getSingleResult();
        //return sessionFactory.getCurrentSession().get(Dog.class, "1");
    }

}
