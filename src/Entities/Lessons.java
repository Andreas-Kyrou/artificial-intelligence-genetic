package Entities;

public class Lessons {
    private final String name;
    private final String tmima;
    private final int id;
    private final int hours;


    public Lessons(String name,String tmima,String id,int hours){
        this.name=name;
        this.tmima=tmima;
        this.id=Integer.parseInt(id);
        this.hours=hours;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public String getTmima() {
        return tmima;
    }

}
