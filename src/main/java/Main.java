import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        genrateAndWrite();
    }
    public static void genrateAndWrite() throws FileNotFoundException {
        long stamp=1615560000; // or: System.currentTimeMillis(); to get a real-time result
        int sec;
        int input;
        var file=new PrintWriter("src/data.txt");
        for(int i=0;i<10;i++){
            double inputDouble=Math.random();
            if(inputDouble<0.5)input=0;
            else input=1;
            file.println(stamp + ": "+input);
            sec=(int)(Math.random()*5+(11-5));//values from 5 to 10
            stamp+=sec;
        }
        file.close();
    }
    //data.put(Long.valueOf(stamp),Integer.valueOf(input)); put pair to the map, przy sładaniu ,mapy powinno  być przd obliczeniem stampa
    public static void readAndMakeMap(){
        var data=new TreeMap<Long,Integer>();
        data.forEach((k,v)-> System.out.println(k +": "+v));
    }



}



