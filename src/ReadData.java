import Entities.Lessons;
import Entities.Teachers;

import java.io.*;
import java.util.ArrayList;

public class ReadData {

    public static ArrayList<Teachers> readTeachers() {
        File f = null;
        BufferedReader reader = null;
        String line;
        final ArrayList<Teachers> teachers=new ArrayList<Teachers>();
        try {
            f = new File(ReadData.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"\\teachers.txt");
        } catch (NullPointerException e) {
            System.err.println("File not found");//tiponi ena minima sfalmatos oti den mporese na vri to arxio
        }
        try {
            reader = new BufferedReader(new FileReader(f));//dokimazi na aniksi to arxeio txt me onoma p dosame pio pano
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");//kai an den mporesi na to aniksi tiponi minima sfalmatos
        }

        try {
            line = reader.readLine();

            while (line != null) {
                String[] inp=line.split(" ");
                teachers.add(new Teachers(inp[0],inp[1],inp[2],Integer.parseInt(inp[3]),Integer.parseInt(inp[4])));
                line=reader.readLine();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return teachers;
    }
    public static ArrayList<Lessons> readLessons(){
        File f = null;
        BufferedReader reader = null;
        String line;
        final ArrayList<Lessons> lessons=new ArrayList<Lessons>();
        try {
            f = new File(ReadData.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"\\lessons.txt");
        } catch (NullPointerException e) {
            System.err.println("File not found");//tiponi ena minima sfalmatos oti den mporese na vri to arxio
        }
        try {
            reader = new BufferedReader(new FileReader(f));//dokimazi na aniksi to arxeio txt me onoma p dosame pio pano
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");//kai an den mporesi na to aniksi tiponi minima sfalmatos
        }

        try {
            line = reader.readLine();
            while (line != null) {
                String[] inp=line.split(" ");
               // System.out.println(inp[0]);
                if(inp.length<4)return lessons;

                lessons.add(new Lessons(inp[0],inp[1],inp[2],Integer.parseInt(inp[3])));
                line=reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }
}
