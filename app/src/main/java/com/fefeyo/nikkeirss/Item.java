package com.fefeyo.nikkeirss;

/**
 * Created by Sam on 2015/02/21.
 */
public class Item {
    private String title;
    private String date;
    private String link;

    public Item(){
        title = "";
        date = "";
        link = "";
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}
    public String getLink(){return link;}
    public void setLink(String link){this.link = link;}
}
