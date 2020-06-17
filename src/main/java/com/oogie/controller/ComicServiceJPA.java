package com.oogie.controller;

import com.oogie.model.ComiclistEntity;
import com.oogie.model.CredentialsEntity;
import org.antlr.runtime.tree.CommonErrorNode;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;

public class ComicServiceJPA extends BaseServiceJPA{
    public ComicServiceJPA(EntityManager entityManager) {
        super(entityManager);
    }

    public int create(ComiclistEntity ce) {
        entityManager.getTransaction().begin();
        ComiclistEntity comiclistEntity = clone(ce);
        entityManager.persist(comiclistEntity);
        Query query = entityManager.createNativeQuery("select max(id) from comiclist");
        int val = (int) query.getSingleResult();
        entityManager.getTransaction().commit();
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

    public List<ComiclistEntity> retrieve(ComiclistEntity ce) {
        if (isEmpty(ce)) {
            return Collections.emptyList();
        }
        StringBuilder sb = new StringBuilder("select c from ComiclistEntity c where 1 = 1 ");
        if (!isNullOrEmpty(ce.getComicname())) {
            sb.append(" and comicname = '").append(ce.getComicname()).append("'");
        }
        if (!isNullOrEmpty(ce.getIssue().toString())) {
            sb.append(" and issue = ").append(ce.getIssue());
        }
        if (!isNullOrEmpty(ce.getWriter())) {
            sb.append(" and writer = '").append(ce.getWriter()).append("'");
        }
        if (!isNullOrEmpty(ce.getArtist())) {
            sb.append(" and artist = '").append(ce.getArtist()).append("'");
        }
        if (!isNullOrEmpty(ce.getYear().toString())) {
            sb.append(" and year = ").append(ce.getYear());
        }
        if (!isNullOrEmpty(ce.getGenre())) {
            sb.append(" and genre = '").append(ce.getGenre()).append("'");
        }
        System.out.println(sb);
        TypedQuery<ComiclistEntity> query = entityManager.createQuery(sb.toString(), ComiclistEntity.class);
        List<ComiclistEntity> comiclistEntities = query.getResultList();
        return comiclistEntities;
    }

    private boolean isEmpty(ComiclistEntity ce) {
        return (isNullOrEmpty(ce.getComicname()) && ce.getIssue() == null && isNullOrEmpty(ce.getWriter()) &&
                isNullOrEmpty(ce.getArtist()) && isNullOrEmpty(ce.getPublisher()) && ce.getYear() == null &&
                isNullOrEmpty(ce.getGenre()));
    }

    public void update(ComiclistEntity ce, int id) {
        ComiclistEntity comiclistEntity = entityManager.find(ComiclistEntity.class, id);
        entityManager.getTransaction().begin();
        if (!isNullOrEmpty(ce.getComicname())) {
            comiclistEntity.setComicname(ce.getComicname());
        }
        if (!isNullOrEmpty(ce.getIssue().toString())) {
            comiclistEntity.setIssue(ce.getIssue());
        }
        if (!isNullOrEmpty(ce.getWriter())) {
            comiclistEntity.setWriter(ce.getWriter());
        }
        if (!isNullOrEmpty(ce.getArtist())) {
            comiclistEntity.setArtist(ce.getArtist());
        }
        if (!isNullOrEmpty(ce.getYear().toString())) {
            comiclistEntity.setYear(ce.getYear());
        }
        if (!isNullOrEmpty(ce.getGenre())) {
            comiclistEntity.setGenre(ce.getGenre());
        }
        entityManager.getTransaction().commit();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        ComiclistEntity comiclistEntity = entityManager.find(ComiclistEntity.class, id);
        entityManager.remove(comiclistEntity);
        entityManager.getTransaction().commit();
    }
}
