package com.example.newsviewpager_tts.unity;

import io.realm.RealmObject;

public class Post extends RealmObject {
    private int id;
    private String linkthumbail;
    private String linkpost;
    private String tittle;
    private String fromnew;
    private String timepost;
    private String type;

    public Post() {
    }

    public String getLinkthumbail() {
        return linkthumbail;
    }

    public void setLinkthumbail(String linkthumbail) {
        this.linkthumbail = linkthumbail;
    }

    public Post(String linkthumbail, String linkpost, String tittle, String fromnew, String timepost) {
        this.linkthumbail = linkthumbail;
        this.linkpost = linkpost;
        this.tittle = tittle;
        this.fromnew = fromnew;
        this.timepost = timepost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkpost() {
        return linkpost;
    }

    public void setLinkpost(String linkpost) {
        this.linkpost = linkpost;
    }

    public String getTittle() {
        return tittle;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getFromnew() {
        return fromnew;
    }

    public void setFromnew(String fromnew) {
        this.fromnew = fromnew;
    }

    public String getTimepost() {
        return timepost;
    }

    public void setTimepost(String timepost) {
        this.timepost = timepost;
    }
}
