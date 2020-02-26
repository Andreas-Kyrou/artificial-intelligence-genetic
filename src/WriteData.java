import Genetic.Chromosome;
import Genetic.Timetable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    public static void print(Chromosome x, Timetable timetable) {
        try (FileWriter writer = new FileWriter("schedule.txt");
             BufferedWriter txt = new BufferedWriter(writer)) {
        int[] genes = x.getGenes();
        int i = 0, j = 1;
        int tmp = 12;
        for (int tmima = 0; tmima < 9; tmima++) {
            switch (tmima) {
                case 0:
                    txt.write("\nA1");
                    break;
                case 1:
                    txt.write("\nA2");
                    break;
                case 2:
                    txt.write("\nA3");
                    break;
                case 3:
                    txt.write("\nB1");
                    break;
                case 4:
                    txt.write("\nB2");
                    break;
                case 5:
                    txt.write("\nB3");
                    break;
                case 6:
                    txt.write("\nG1");
                    break;
                case 7:
                    txt.write("\nG2");
                    break;
                case 8:
                    txt.write("\nG3");
                    break;

            }
                    for (int day = 0; day < 5; day++) {
                        txt.write("\n");
                        switch (day) {
                            case 0:
                                txt.write("\nDeftera\n");
                                break;
                            case 1:
                                txt.write("Triti\n");
                                break;
                            case 2:
                                txt.write("Tetarti\n");
                                break;
                            case 3:
                                txt.write("Pempti\n");
                                break;
                            case 4:
                                txt.write("Paraskevi\n");
                                break;
                        }
                        while (i < tmp) {
                            txt.write("Kathigitis: " + timetable.getTeacher(genes[i]).getName() + "\t\t");
                            i += 2;
                        }
                        txt.write("\n");
                        while (j < tmp) {
                           txt.write("Mathima: " + timetable.getLesson(genes[j]).getName() + "    \t\t");
                            j += 2;
                        }
                        txt.write("\n");
                        tmp+=12;
                    }
                txt.write("\n");
            }
        txt.write("Score of schedule: "+x.getScore()+".");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    }
