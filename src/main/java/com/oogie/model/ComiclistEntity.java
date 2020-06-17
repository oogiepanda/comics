package com.oogie.model;

import javax.persistence.*;

@Entity
@Table(name = "comiclist", schema = "comics", catalog = "")
public class ComiclistEntity {
    private int id;
    private String comicname;
    private Integer issue;
    private String writer;
    private String artist;
    private String publisher;
    private Integer year;
    private String genre;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comicname", nullable = false, length = 20)
    public String getComicname() {
        return comicname;
    }

    public void setComicname(String comicname) {
        this.comicname = comicname;
    }

    @Basic
    @Column(name = "issue", nullable = true)
    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    @Basic
    @Column(name = "writer", nullable = true, length = 20)
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Basic
    @Column(name = "artist", nullable = true, length = 20)
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Basic
    @Column(name = "publisher", nullable = true, length = 20)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "year", nullable = true)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "genre", nullable = true, length = 10)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComiclistEntity that = (ComiclistEntity) o;

        if (id != that.id) return false;
        if (comicname != null ? !comicname.equals(that.comicname) : that.comicname != null) return false;
        if (issue != null ? !issue.equals(that.issue) : that.issue != null) return false;
        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (comicname != null ? comicname.hashCode() : 0);
        result = 31 * result + (issue != null ? issue.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
