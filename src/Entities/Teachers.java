package Entities;

public class Teachers {
    private final int id;
    private final String name;
    private final int[] lessons;
    private final int maxPerDay;
    private final int maxPerWeek;
    public Teachers(String id,String name,String lessons,int maxPerDay,int maxPerWeek){
        this.id=Integer.parseInt(id);
        this.name=name;
        this.maxPerDay=maxPerDay;
        this.maxPerWeek=maxPerWeek;
        String[] lessons_p=lessons.split(",");
        this.lessons= new int[lessons_p.length];
       for(int i=0;i<lessons_p.length;i++){
           this.lessons[i]=Integer.parseInt(lessons_p[i]);
       }
    }

    public int getMaxPerDay() {
        return maxPerDay;
    }

    public int getMaxPerWeek() {
        return maxPerWeek;
    }
    

    public int getid() {
        return id;
    }

    public int[] getLessons() {
        return lessons;
    }

    public String getName() {
        return name;
    }



}
