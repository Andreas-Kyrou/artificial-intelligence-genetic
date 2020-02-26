package Genetic;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private int[] genes;
    private int score;
    private static Timetable timetable;
    public Chromosome(Timetable timetable){
        ArrayList<Integer> hours=new ArrayList<>();//15.enas pinakas me tis ores tou kathe mathimatos
        for(int i=0;i<timetable.getLessons().size();i++)//16.gemizo ton pinaka me 0
            hours.add(0);
        this.timetable=timetable;
        int chromosomeLength=540;//17. to megethos tou xromosomatos einai 540= (5 meres * 6 ores mathimatos ana mera * 9 tmimata) * ( 1 eggrafi g kathigiti + 1 eggrafi g mathima)
        genes =new int[chromosomeLength];//18.arxikopio to chromosoma
        int teacher,lesson,p;
        int index=0;//
        int teachersLength=timetable.getTeachersLength();//19.perno to plithos ton kathigiton
        Random r=new Random();
        for(int k=0;k<9;k++){//20.gia ta 9 tmimata
            for(int j=0;j<30;j++){//21.apo 30 ores didaskalias ana vdomada

                teacher=r.nextInt(teachersLength)+1;//22.dialegoyme tixea enan kathigiti
                int[] lessons=timetable.getTeacher(teacher).getLessons();//23.perno ton pinaka me ta mathimata p kani o kathigitis p epileksame tixea
                lesson=lessons[r.nextInt(lessons.length)];//24.dialego ena apo ta mathiamta p didaski o kathigitis

                while(hours.get(lesson-1)==timetable.getLesson(lesson).getHours()) {//25.an to mathima to exoume ksanadialeksi kai exoun simplirothi i ores didaskalias tou
                    teacher=r.nextInt(teachersLength)+1;//26.dialekse ena allo kathigiti

                    lessons=timetable.getTeacher(teacher).getLessons();//27.dialekse allo mathima apo tin lista tou neou kathigiti
                    lesson=lessons[r.nextInt(lessons.length)];//
                }
                p = hours.get(lesson-1);//28.pernoume tis ores tou mathimatos p dialeksame
                p++;//29.auksanoume tis ores tou
                hours.add(lesson-1,p);//30.to apothikevoume ston pinaka
                genes[index]=teacher;//31.vale ton kathigiti
                index++;//32.auksise to index kata ena
                genes[index]=lesson;//33.vazoume to mathima p dialeksame
                index++;//34.auksanoume to index

            }
            for( int z=0;z<timetable.getLessons().size();z++)//
                hours.add(0);//35.ksana gemizoume ton pinaka me 0 gia to neo mathima
        }
        this.calculateScore();//36.kaloume tin calculate score g na ipologisoume to score
    }

    public Chromosome(int[] childgenes) {//
        this.genes=new int[540];
        for(int i=0;i<540;i++)
            this.genes[i]=childgenes[i];
        this.calculateScore();
    }

    private void calculateScore() {
        this.score=firstRestriction()+secondRestriction()+thirdRestriction()+fourthRestriction();//37.kaloume tis sinartisis g ts 4 periorismous p exoume
    }

    private int firstRestriction() {//elegxo an o kathigitis didaski tin idia ora se diaforetika tmimata
        int score_fi=0;//38.arxikopio to score p exo me 0
        int i=0;//39.arxikopio to i me 0
        int j;
        boolean check=false;
        while(i<480) {//40.g olo tn pinaka plin 60 theseon
            j=i+60;//41.vazoume to j iso me to i sin 60 g na eleksoume an o idios kathigitis kani tin idia ora mathima k se allo tmima
            while(j<540){//42.g olo ton pinaka
                if(genes[i]==genes[j]&& !check)//43.elegxo an o kathigitis kani mathiam tin idia ora se 2 tmimata
                    check=true;//44.an isxiei kane tin meta vliti true
                j+=60;//45.aksano to j kata 60
            }
            if(check==false)//46.an vrikame oti o kathigitis den kani se 2 tmimata tin idia ora
                score_fi++;//47.auksanoume to score
            check=false;//48.kanoume ksana false tin metavliti elegxou
            i+=2;//49.auksanoume to i kata 2 g na paroume ton epomeno kathigiti
        }
        return score_fi*4;//50.pollaplasiazoume to score epi 4 g na tou dosoume megaliteri simasia apo allo periorismo
    }

    private int secondRestriction(){//elegxo an o kathigitis didaski parapano apo 2 sinexomenes ores
        int score_s=0;
        int i=0,j;
        int tmp=8;
        while(i<539){//51.diatrexo tonpinaka
            j=i+2;//52.to j isoute me i+2
            if((genes[i]==genes[j] && genes[i]!=genes[j+2]) || (genes[i]==genes[j] && i==tmp))//an o kathigitis didaski 2 ores sinexomena alla oxi 3 sinexomeni
                score_s++;//53.auksise to score
            if(genes[i]!=genes[j])//54.an dn kani 2 or sinexomeni
                score_s++;//55.auksise to score
            if(i==tmp)
                tmp+=12;//56.g na kseroume pote eftase sto telos tis meras opote an vriskete 3 fores sinexomena alla i mia einai alli mera den iparxi provlima
            i+=2;
        }
        return score_s*3;//57.pollaplasiazo to score epi 3 g na dosoume diaforetiki varitita apo tous ipolopous periorismous
    }

    private int thirdRestriction(){//elegxo an o arithmos mathimatos mesa se mia mera kseperna ton evdomadiaio arithmo oron
        ArrayList<Integer> lessonscheck=new ArrayList<>();//58.arxikopio ena pinaka me ta id ton mathimaton p tha eleksoume
        int i=1,j,tmp=12;//59.ksekinoume apo tin thesi 1 opou vriskete to proto mathima kai to tmp iso me 12 gt mas endiaferi i kathe mera ksexorista
        int counter;//60.counter g na metroume poses fores emfanizete to mathima
        int score_t=0;
        while(i<539){//61.diatrexo to xromosomo
            j=i+2;//62.j iso me i+2 g na elekso to epomeno tou mathima
            if(!lessonscheck.contains(genes[i])){//63.an to mathima den to eleksame
                counter=0;//64.kane ton counter 0
                lessonscheck.add(genes[i]);//65.vale ston pinaka to mathima
                while(j<tmp){//66.diatrexi tin ipolipi mera
                    if(genes[i]==genes[j])//67.an vri to idio mathima ksana
                        counter++;//68.auksani ton counter kata ena
                    j+=2;//69.j+2 g to epomeno mathima
                }
                if(counter<(timetable.getLesson(genes[i]).getHours()/2)){//70.an to mathima didaskete ligotero apo tes mises ores p einai diathesimo
                    score_t++;//71.auksise to score
                }
            }
            i+=2;//72.piase to epomeno mathima
            if(i==tmp-1){//73.otan eleksoume oli tin mera
                tmp+=12;//74.auksise to tmp kata 12 g na eleksoume tin epomeni mer
                i+=2;
                lessonscheck.clear();//75.adiase ton pinaka
            }
        }
        return score_t*5;
    }

    private int fourthRestriction(){//elegxo an o arithmos ton didaskomenon oron mesa stin evdomada einai megaliteros apo ton dosmeno
        ArrayList<Integer> lessonscheck=new ArrayList<>();//76.pinakas me ta mathimata p tha elegxoume
        int i=1,j,tmp=60;//77.tora epd tha elegxoume g oli tin evdomada vazoume to tmp iso me 60
        int counter;//
        int score_f=0;
        while(i<539){
            j=i+2;
            if(!lessonscheck.contains(genes[i])){
                counter=0;
                lessonscheck.add(genes[i]);
                while(j<tmp){
                    if(genes[i]==genes[j])
                        counter++;
                    j+=2;
                }
                if(counter==(timetable.getLesson(genes[i]).getHours())) {//78.an i ores p didasketeto mathima einai ises me utes p prepi auksanoume to  score
                    score_f++;
                }
            }
            i+=2;
            if(i==tmp-1){//79.an eftase sto telos tis vdomadas
                tmp+=60;//79.auksanoume to tmp kata 60 g na paroume tin epomeni vdomada
                i+=2;
                lessonscheck.clear();
            }
        }
        return score_f*7;
    }

    public int getScore() {
        return score;
    }

    public int[] getGenes() {
        return genes;
    }

    public void mutate() {
        Random r=new Random();
        int index;
        int tm=0;
        Random r_t=new Random();
        for(int i=0;i<9;i++){//80. tha alaksoume mia periodo didaskalias g kathe tmima
            index=r_t.nextInt(59)+tm;//81.epilegoume mia tixea thesi tou pinaka
            if(index%2!=0)index++;//82.an o arithmos p epilekse tixea den einai zigos auksanoume to index kata ena g na allaksoume sosta tin periodo logo tou oti exoume 2 eggrafes g kathe periodo
            genes[index]=r.nextInt(timetable.getTeachersLength())+1;//83.epilegoume kainourgio kathigiti tixea
            int[] lesson=timetable.getTeacher(genes[index]).getLessons();//84.pernoume ta mathimata p mpori na didaksi o kathigitis
            genes[index+1]=lesson[r.nextInt(lesson.length)];//85.epilegoume ena neo mathima apo ton pinaka
            tm+=59;//86.auksanoume to tm kata 59 g na allaksoume periodo didaskalias allou tmimatos
        }
    }
    @Override
    public int compareTo(Chromosome x) {
       return this.score-x.score;
    }

    /* private int fifthRestriction(){//elegxo an kseperasame ton arithmon oron didaskalia ana mera me tis ores tou kathigiti



    }

    private int sixthRestrinction() {

    }*/
}
