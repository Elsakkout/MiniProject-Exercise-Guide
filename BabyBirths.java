import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr. getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1)+
                                " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr){
        int countBoys = 0;
        int countGirls = 0;
        int countTotal = 0;
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr. getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                countBoys++;
            } else {
                totalGirls += numBorn;
                countGirls++;
            }
            countTotal++;
        }
        System.out.println("number of boys' names " + countBoys);
        System.out.println("number of girls' names " + countGirls);
        System.out.println("number of total names " + countTotal);
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
        int rank = 0;
        int number = 0; 
        String na = "yob" + year + ".csv";
        FileResource fr = new FileResource(na);
        for (CSVRecord rec : fr. getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank = rank + 1;
                if (rec.get(0).equals(name)){
                    number = 1;
                    break;
                }
            }             
        }
        
        if (number == 1){
                return rank;
            } else {
                return -1;
            }
    }
    
    public void testGetRank(){
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println(name +"'s rank is " + rank);
    }
    
    public String getName(int year, int rank, String gender){ 
        String na = "yob" + year + ".csv";
        int number = 0;
        String line = null;
        int check = 0;
        FileResource fr = new FileResource(na);
        for (CSVRecord rec : fr. getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                number = number + 1;
                if (number == rank){
                    line = rec.get(0);
                    check = 1;
                }
            }
        }
        
        if (check == 1){
            return line;
        } else {
            return "NO NAME";
        }
    }
    
    public void testGetName(){
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println("The name is " + getName(year, rank, gender));
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        year = 1974;
        name = "Owen";
        gender = "M";
        int rank = getRank (year, name, gender);
        String line = null;
        int check = 0;
        int number = 0;
        String newNa = "yob" + newYear + ".csv";
        FileResource fr = new FileResource(newNa);
            for (CSVRecord rec : fr. getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                number = number + 1;
                if (number == rank){
                    line = rec.get(0);
                    check = 1;
                }
            }
        }
        
        if (check == 1){
            return line;
        } else {
            return "NO NAME";
        }
        
        
        
    }
        
    public void testWhatIsNameInYear(){
        int year = 1974;
        String name = "Owen";
        String gender = "M";
        int newYear = 2014;
        System.out.println(name +" born in " + year + " would be " 
                            + whatIsNameInYear(name, year, newYear, gender) 
                            +" if he/she was born in " + newYear);
    }    
        
    public int yearOfHighestRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int check = 0;
        int checkAll = 0;
        int yearNum = 0;
        String file = null;
        String newFile = null;
        for (File f : dr.selectedFiles()){
          String fileName = f.getName();
          int rank = 0;
          FileResource fr = new FileResource(fileName);
            for (CSVRecord rec : fr. getCSVParser(false)){
              if (rec.get(1).equals(gender)){
                rank = rank + 1;
                if (rec.get(0).equals(name)){
                    check = 1;
                    break;
                }
              }
            }
            
            if (highestRank == 0){
                highestRank = rank;
            }

            if (highestRank < rank){
               
               highestRank = highestRank;
              } else {
                highestRank = rank;
                file = f.getName();
                
                }
              
                
              String year = file.substring(3,7);
              yearNum = Integer.parseInt(year);
              System.out.println(highestRank);
              System.out.println(yearNum);
            }
        if (check == 1){
            checkAll = 1;
         }
         
        if (checkAll == 0){
            return -1;
        } else {
            return yearNum;
        }
        
    }
    
    public void testyearOfHighestRank (){
        String name = "Mich";
        String gender = "M";
        System.out.println(yearOfHighestRank(name, gender));
    }
    
    public double getAverageRank(String name, String gender){
        int count = 0;
        double average = 0.0;
        double rankTotal = 0.0;
        int check = 0;
        int checkAll = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
          String fileName = f.getName();
          double rank = 0.0;
          FileResource fr = new FileResource(fileName);
            for (CSVRecord rec : fr. getCSVParser(false)){
              if (rec.get(1).equals(gender)){
                rank = rank + 1;
                if (rec.get(0).equals(name)){
                    check = 1;
                    break;
                }
              }
            }
          rankTotal += rank;
          count++;
        }
        average = rankTotal/count;
        if (check == 1){
            checkAll = 1;
         }
         
        if (checkAll == 0){
            return -1;
        } else {
            return average;
        }
    }
    
    public void testGetAverageRank(){
        String name = "Robert";
        String gender = "M";
        System.out.println(getAverageRank(name, gender));
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int check = 0;
        int totalRankedHigher = 0;
        String na = "yob" + year + ".csv";
        FileResource fr = new FileResource(na);
        for (CSVRecord rec : fr. getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){
                    check = 1;
                    break;
                }
                totalRankedHigher += numBorn;
            }
        }
        
        if (check == 1){
            return totalRankedHigher;
        } else {
            return -1;
        }
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(year, name, gender));
    }
}