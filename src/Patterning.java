import java.io.*;
import java.util.*;

public class Patterning {
    static ArrayList<Integer> first = new ArrayList<Integer>(),second = new ArrayList<Integer>(),third = new ArrayList<Integer>();
    public Patterning(String file,int width){
        try{
            String outputfile=file.substring(0, file.length()-4)+"(Patterning).raw";
            FileOutputStream MyOutputFile=new FileOutputStream(outputfile);
            FileInputStream MyInputFile=new FileInputStream(file);
            int value;
            int i=0;
            while((value=MyInputFile.read())!=-1){
                i++;
                findRange(value);
                if(i>width-1){
                    for(int j=0;j<first.size();j++){
                        MyOutputFile.write(first.get(j));
                    }
                    for(int j=0;j<second.size();j++){
                        MyOutputFile.write(second.get(j));
                    }
                    for(int j=0;j<third.size();j++){
                        MyOutputFile.write(third.get(j));
                    }
                    first.clear();
                    second.clear();
                    third.clear();
                    i=0;
                }
            }
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
    }
    
    static void findRange(int value){
        if(value<26){
            first.addAll(Arrays.asList(0, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 0));
        }else if(value<52){
            first.addAll(Arrays.asList(0, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        }else if(value<78){
            first.addAll(Arrays.asList(255, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        }else if(value<103){
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        }else if(value<130){
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(255, 0, 255));
        }else if(value<156){
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        }else if(value<183){
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(255, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        }else if(value<208){
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        }else if(value<234){
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 0, 255));
            third.addAll(Arrays.asList(255, 255, 255));
        }else{
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 255, 255));
            third.addAll(Arrays.asList(255, 255, 255));
        }
    }
}
