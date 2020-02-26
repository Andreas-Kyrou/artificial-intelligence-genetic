package Genetic;
import Entities.Lessons;
import Entities.Teachers;

import java.util.HashMap;

public class Timetable {
    private final HashMap<Integer, Teachers> teachers;
    private final HashMap<Integer, Lessons> lessons;
    private int num=0;
    public Timetable() {
        this.teachers=new HashMap<Integer,Teachers>();//5.arxikopio ta hashmap
        this.lessons=new HashMap<Integer,Lessons>();
    }
    public Timetable(Timetable oldTable){
        this.lessons=oldTable.getLessons();
        this.teachers=oldTable.getTeachers();
    }
    public HashMap<Integer, Lessons> getLessons(){return this.lessons;}//epistrefo to hashmap ton mathimaton

    public HashMap<Integer, Teachers> getTeachers(){return this.teachers;}//epistrefo to hash map ton kathigiton

    public void addTeachers(int id,Teachers teacher){
        this.teachers.put(id,teacher);
    }//vazo kenourgio kathigiti sto hash map

    public void addLessons(int id,Lessons lesson){
        this.lessons.put(id,lesson);
    }//vazo neo mathima sto hashmap ton mathimaton

    public Lessons getLesson(int id){return (Lessons) this.lessons.get(id);}//epistrefo to mathima me vasi to id tou

    public Teachers getTeacher(int id){return (Teachers) this.teachers.get(id);}//epistrefo ton kathigiti me vasi to id tou

    public int getTeachersLength(){
        return teachers.size();
    }

}