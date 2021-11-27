import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException{
        genrateAndWrite();
        TreeMap<Long,Integer>data= readAndMakeMap();
        TreeMap<Long,Integer>processedData=makeTreeMapOfChanges(data);
        Stream<Map.Entry<Long,Integer>> outputStream =processedData         //creating stream of map's entries
                .entrySet()
                .stream();
        outputStream.forEach(e-> System.out.println(e.getKey()+": "+e.getValue()));         //display outputStream's view


    }
    public static void genrateAndWrite() throws FileNotFoundException {
        long stamp=1615560000;           // or: System.currentTimeMillis(); to get a real-time result
        int sec;                        // random seconds
        int input;                        //value 0 or 1, picked randomly
        var file=new PrintWriter("src/data.txt");           //where to print data
        for(int i=0;i<10;i++){
            double inputDouble=Math.random();
            if(inputDouble<0.5)input=0;
            else input=1;
            file.println(stamp + ": "+input);
            sec=(int)(Math.random()*5+(11-5));              //values from 5 to 10
            stamp+=sec;                                     //updating variable stamp
        }
        file.close();
    }
    public static TreeMap<Long,Integer> readAndMakeMap() throws IOException {
        Scanner in=new Scanner(new File("src/data.txt"));            //source to read
        List<String>words=new LinkedList<>();                   //list of all words in the source
        while(in.hasNext()){                                // reading from source
            String word=in.next();
            words.add(word);                                //filling the words List
        }

        List<Long> stamps=words                                 //creating list of stamps
                .stream()
                .filter(x->x.endsWith(":"))                     //filtring stamps
                .map(x->x.replace(":",""))      //cutting unnessesary characters ":"
                .map(x->Long.parseLong(x))                      //converting Strings to Longs
                .collect(Collectors.toList());                  //filling the stamps List


        List<Integer> inputs=words                      //creating list of inputs
                .stream()
                .filter(x->x.length()==1)                       //filtring inputs
                .map(x->Integer.parseInt(x))                    //converting Strings to Integers
                .collect(Collectors.toList());                  // //filling the inputs List


        var data=new TreeMap<Long,Integer>();                   //creating map with stamps as keys and inputs as values
        ListIterator<Long>stampsLi=stamps.listIterator();       //creating listIterator for stamps
        ListIterator<Integer>inputsLi=inputs.listIterator();    //creating listIterator for inputs

        while(stampsLi.hasNext()){
            data.put(stampsLi.next(), inputsLi.next());         // filling the data Map
        }

        return data;                                            //return TreeMap
    }
    public static TreeMap<Long,Integer> makeTreeMapOfChanges(TreeMap<Long,Integer> treeMap){

        int previousInput=2;                                           //variable for monitorng values
        TreeMap<Long,Integer>processedMap=new TreeMap<>();              //create map for results
        Iterator<Map.Entry<Long, Integer>> treeMapIt = treeMap.entrySet().iterator();   // create interator for TreeMap<Long, Integer> parameter
        while (treeMapIt.hasNext()) {
            Map.Entry<Long, Integer> entry = treeMapIt.next();              //go to the next entry
            if (entry.getValue()!= previousInput){                        //if prevous value is different,
                processedMap.put(entry.getKey(),entry.getValue());        //put entry to the results map
                previousInput =entry.getValue();                          // set previousInput to the value
            }                                                              //of entry
        }
        return processedMap;                    //return results
    }


}



