package Genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Genetic {
    private int populationSize;
    private double mutationProbability;
    private int minimumFitness;
    private int maximumSteps;
    private ArrayList<Chromosome> population;
    private ArrayList<Integer> score;
    
    public Genetic(int populationSize,double mutationProbability,int minimumFitness,int maximumSteps){
        this.maximumSteps=maximumSteps;
        this.minimumFitness=minimumFitness;
        this.populationSize=populationSize;
        this.mutationProbability=mutationProbability;
    }

    public Chromosome geneticAlgorithm(Timetable timetable) {
        initializePopulation(this.populationSize,timetable);//11.dimiourgo ton proto plithismo xromosomaton
        Random r=new Random();
        int max=0;
        for(int step=0;step<maximumSteps;step++){//18.g osa steps thesame na ektelesti o genetikos algorithmos
            ArrayList<Chromosome> newPopulation=new ArrayList<>();//19.arxikopioume ena neo pinaka p tha valoume ta nea xromosomata
            for (int i=0;i<populationSize;i++) {//20.g oso plithismo xromosomaton eepithimoume
                int xIndex = this.score.get(0);
                Chromosome x=this.population.get(0);//21.pernoume to xromosoma stin proti thesi opote auto me to megalitero score

                int yIndex=this.score.get(0+x.getScore());//
                while(yIndex==xIndex){
                    yIndex=this.score.get(r.nextInt(this.score.size()));

                }
                Chromosome y=this.population.get(1);//22.pernoume to xromosoma me to deutero pio ipsilo score

                Chromosome child=this.reproduce(x,y);//23.kalite i reproduce g na paroume to pedi tous
                if(r.nextDouble()<mutationProbability){//24.analoga me tin pithanotita p dosame
                    child.mutate();//25.kalite i mutate g na kani tixees allages sto programma
                }
                newPopulation.add(child);//26.vazoume ta nea pedia ston pinaka
            }
            this.population=new ArrayList<Chromosome>(newPopulation);//27.o neos plithismos xromosomaton einai ta pedia ton palion
            Collections.sort(this.population,Collections.reverseOrder());//28.taksinomi ton plithismo me auksonta arithmo

            if(this.population.get(0).getScore()>=minimumFitness){//29.an ftasoume to score
                System.out.println("Finished after "+step+"steps...");
                return this.population.get(0);//30.epistrefoume to proto xromosoma
            }
            this.updateScore();//31.kaloume tin update score etsi oste na ftaxti ksana sosta o pinakas
        }
        System.out.println("Finished after " + maximumSteps + " steps...");

        return this.population.get(0);//32.an kseperasoume ta vimata epistrefoume to prooto xromosoma
    }

    private Chromosome reproduce(Chromosome x, Chromosome y) {
        Random r=new Random();
        int[] childgenes_2=new int[540];
        int pivot=r.nextInt(540)+1;//24.dialegoume tin stili stin opoia tha kopsoume ta dio xromosomata
        if(pivot % 2!=0)//25.an o arithmos einai monos
            pivot++;//26.to auksanoume kata ena g na gini sosta to kopsimp
        int [] childgenes=new int[540];//27.to neo xromosoma
        for(int i=0;i<pivot;i++)//28.apo tin proti thesi tou xromosomatos x mexri tin tixea stili p epilextike
            childgenes[i]=x.getGenes()[i];//29.vale sto neo xromosoma tis times tou xromosomatos x
        for(int i=pivot;i<childgenes.length;i++)//30.stin sinexia gemise to neo xromosoma me tis times tou y
            childgenes[i]=y.getGenes()[i];
        Chromosome ena=new Chromosome(childgenes);//31.dimiourgoume to ena apo ta dio pedia

        //edo pernoume to deutero pedi me tis protes thesis na einai tou y kai meta tou x
        for(int i=0;i<pivot;i++)//32.apo tin proti thesi tou xromosomatos y mexri tin tixea stili p epilextike
            childgenes_2[i]=y.getGenes()[i];//33.vale sto neo xromosoma tis times tou xromosomatos y
        for(int i=pivot;i<childgenes.length;i++)//30.stin sinexia gemise to neo xromosoma me tis times tou x
            childgenes_2[i]=x.getGenes()[i];
        Chromosome dio=new Chromosome(childgenes_2);//dimiourgoume to deftero pedi*/

        if(ena.getScore()>dio.getScore())//31.epistrefo to kalitero pedi
            return ena;
        else
            return dio;
    }

    private void initializePopulation(int populationSize,Timetable timetable) {
        this.population=new ArrayList<Chromosome>();//12.o pinakas me ta xromosomata
        for (int i=0;i<populationSize;i++){//
            this.population.add(new Chromosome(timetable));//14.dimiourgo kathe fora neo xromosoma
        }
        this.updateScore();//15.kalite i updatescore g na vali taxromosomata toses fores oses to score
    }

    private void updateScore() {
        this.score=new ArrayList<>();
       for (int i=0;i<this.population.size();i++){//16.diatrexo ton pinaka me ta xromosomata
           for (int j=0;j<this.population.get(i).getScore();j++)//17.oso einai to score tou xromosomatos
                score.add(i);//18.valto toses fores ston pinaka.
        }
    }
}
