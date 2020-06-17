package com.oogie.controller;

import com.oogie.model.ComiclistEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComicServiceJPATest {
    private static EntityManagerFactory emfactory;
    private static EntityManager entityManager;
    private static ComicServiceJPA comicServiceJPA;

    @BeforeAll
    public static void config() {
        emfactory = Persistence.createEntityManagerFactory("comics");
        entityManager = emfactory.createEntityManager();
        comicServiceJPA = new ComicServiceJPA(entityManager);
    }

    @AfterAll
    public static void destroy() {
        entityManager.close();
        emfactory.close();
    }

    private ComiclistEntity createComicListEntity() {
        ComiclistEntity comiclistEntity = new ComiclistEntity();
        comiclistEntity.setComicname("Spiderman");
        comiclistEntity.setIssue(1);
        comiclistEntity.setWriter("Stan Lee");
        comiclistEntity.setArtist("Jack Kirby");
        comiclistEntity.setYear(1964);
        comiclistEntity.setGenre("Action");
        return comiclistEntity;
    }

    @Test
    void crud() {
        ComiclistEntity comiclistEntity = createComicListEntity();
        int id = comicServiceJPA.create(comiclistEntity);
        List<ComiclistEntity> comics = comicServiceJPA.retrieve(comiclistEntity);
        assertThat(comics.get(0).getComicname(), is(comiclistEntity.getComicname()));

        comics.get(0).setComicname("Captain America");
        comicServiceJPA.update(comics.get(0),comics.get(0).getId());
        comicServiceJPA.delete(id);
        List<ComiclistEntity> lastComics = comicServiceJPA.retrieve(comiclistEntity);
        assertThat(lastComics.size(), is(0));
    }
}