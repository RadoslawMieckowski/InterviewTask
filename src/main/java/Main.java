import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException{
        genrateAndWrite();
        readAndMakeMap();
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
    public static void readAndMakeMap() throws IOException {
        Scanner in=new Scanner(new File("src/data.txt"));
        //var contents=new String(Files.readAllBytes(Paths.get("src/data.txt")), StandardCharsets.UTF_8); nigdy nie używaj tego  ze strumieniami!!
        List<String>words=new LinkedList<>();
        while(in.hasNext()){
            String word=in.next();
            words.add(word);
        }

        List<Long> stamps=words
                .stream()
                .filter(x->x.endsWith(":"))
                .map(x->x.replace(":",""))
                .map(x->Long.parseLong(x))
                //.mapToLong(x -> Long.parseLong(x)) użycie tego uniemożliwia użycie .collect(Collectors.toList())
                .collect(Collectors.toList());
        for(Long x :stamps){
            System.out.println(x);
        }


        List<Integer> inputs=words
                .stream()
                .filter(x->x.length()==1)
                .map(x->Integer.parseInt(x))
                .collect(Collectors.toList());
        for(int x :inputs){
            System.out.println(x);
        }

        var data=new TreeMap<Long,Integer>();
        ListIterator<Long>stampsLi=stamps.listIterator();
        ListIterator<Integer>inputsLi=inputs.listIterator();

        while(stampsLi.hasNext()){
            data.put(stampsLi.next(), inputsLi.next());
        }
        data.forEach((k,v)-> System.out.println(k +": "+v));
    }



}



