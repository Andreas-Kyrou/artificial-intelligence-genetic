import Entities.Lessons;
import Entities.Teachers;
import Genetic.Genetic;
import Genetic.Chromosome;
import Genetic.Timetable;

import java.util.ArrayList;

public class Main {
    public  static void main(String[] args)
    {
        Timetable timetable=initTimetable();//1.kalo tin sinartisi initTimetable

        Genetic gene=new Genetic(90,0.06,3050,10000);//9.dimiourgo ena antikimeno genetic

        Chromosome x=gene.geneticAlgorithm(timetable);//10.kalite i geneticAlgorithm me orisma to timetable

        WriteData.print(x,timetable);

    }

    private static Timetable initTimetable(){
        ArrayList<Lessons> lessons=ReadData.readLessons();//2.kalo tin readLesson g na diavasi ta mathimata apo to txt ka epistrefi ena arraylist
        ArrayList<Teachers> teachers=ReadData.readTeachers();//3.diavazo tous kathigites meso tis readTeachers p epistrefi ena arraylist me tous kathigites

        Timetable timetable=new Timetable();//4.dimiourgo ena antikimeno timetable

        for(Teachers teacher:teachers)//5.diatrexo ton arraylist me tous kathigites
            timetable.addTeachers(teacher.getid(),teacher);//6.vazo sto hashmap tou timetable ton kathigiti
        for(Lessons lesson:lessons)//7.diatrexo tin lista me ta mathimata
            timetable.addLessons(lesson.getId(),lesson);//8.vazo sto hashmap tou timetable ta mathimata
        return timetable;//epistrefo to timetable
    }
}
