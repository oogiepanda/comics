package com.oogie.controller;

import com.oogie.model.ComiclistEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ComicServiceJPA extends BaseServiceJPA{
    public ComicServiceJPA(EntityManager entityManager) {
        super(entityManager);
    }

    public int create(ComiclistEntity ce) {
        entityManager.getTransaction().begin();
        ComiclistEntity comiclistEntity = clone(ce);
        entityManager.persist(comiclistEntity);
        Query query = entityManager.createNativeQuery("select max(id) from comics");
        int val = (int) query.getSingleResult();
        return val;
    }

    private ComiclistEntity clone(ComiclistEntity ce) {
        ComiclistEntity clone = new ComiclistEntity();
        clone.setComicname(ce.getComicname());
        clone.setIssue(ce.getIssue());
        clone.setWriter(ce.getWriter());
        clone.setArtist(ce.getArtist());
        clone.setPublisher(ce.getPublisher());
        clone.setYear(ce.getYear());
        clone.setGenre(ce.getGenre());
        return clone;
    }

//    public List<ComiclistEntity> retrieve(ComiclistEntity ce) {
//
//    }
}
