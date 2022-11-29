package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.OfficerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
//import java.util.logging.Logger;

import com.oreilly.persistence.config.JpaQueries;

@SuppressWarnings("JpaQlInspection")
@Repository
public class JpaOfficerDAO implements OfficerDAO<OfficerEntity> {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JpaQueries queries;

    @Override
    public OfficerEntity save(OfficerEntity officerEntity) {
        em.persist(officerEntity);
        return officerEntity;
    }

    @Override
    public Optional<OfficerEntity> findById(Integer id) {
        return Optional.ofNullable(em.find(OfficerEntity.class, id));
    }

    @Override
    public List<OfficerEntity> findAll() {
        return em.createQuery(queries.getFindAllOfficers(), OfficerEntity.class).getResultList();
    }

    @Override
    public long count() {
        return em.createQuery(queries.getGetOfficerCount(), Long.class).getSingleResult();
    }

    @Override
    public void delete(OfficerEntity officer) {
        em.remove(officer);
    }

    @Override
    public boolean existsById(Integer id) {
        Object result = em.createQuery(queries.getExistsById()).setParameter("id", id).getSingleResult();

        return !Objects.isNull(result);
    }
}
