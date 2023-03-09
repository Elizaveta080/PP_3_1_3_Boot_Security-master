package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    private EntityManager em;

    public RoleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Role role) {
        em.persist(role);
    }
}
