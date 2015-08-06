package com.twu.biblioteca;

/**
 * Created by rbagge on 06/08/2015.
 */
public abstract class Media {
    private String name;
    private int id;
    private String year;
    private boolean availability;
    private String creator;

    public Media(String name, int id, String year, String creator) {
        setName(name);
        setId(id);
        setYear(year);
        setCreator(creator);
        setAvailability(true);
    }

    public String getName() {
        return name;
    }
    public String getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public String getCreator(){
        return creator;
    }

    public boolean isAvailable(){
        return availability;
    }

    public boolean setAvailability(boolean availability) {
        this.availability = availability;
        return availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean equals(Object obj){
        if(this.getId() == ((Media) obj).getId() && this.getClass().equals(obj.getClass())){
            return true;
        }else{
            return false;
        }
    }

    public abstract String toString();

}
