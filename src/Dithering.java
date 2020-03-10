import java.io.*;
import java.util.*;

public class Dithering {
    static int[][] DMatrix = {{0,128,32,160},
                              {192,64,224,96},
                              {48,176,16,144},
                              {240,112,208,80}};
    static ArrayList<Integer> paint = new ArrayList<Integer>();
    public Dithering(int d,String file,int width){
        try{
            String outputfile=file.substring(0, file.length()-4)+"(D"+d+").raw";
            FileOutputStream MyOutputFile=new FileOutputStream(outputfile);
            FileInputStream MyInputFile=new FileInputStream(file);
            Dithering(width,d,MyOutputFile,MyInputFile);
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
    }
    
    static void Dithering(int width,int d,FileOutputStream MyOutputFile,FileInputStream MyInputFile){
        int value;
        int i=0;
        int column=0;
        int row=0;
        try{
            while((value=MyInputFile.read())!=-1){
                i++;
                writeBytes(value,DMatrix[row][column]);
                if(d==1){
                    if(column==1){
                        column=0;
                    }else{
                        column=1;
                    }
                }else if(d==2){
                    if(column==3){
                        column=0;
                    }else{
                        column++;
                    }
                }
                if(i>width-1){
                    for(int j=0;j<paint.size();j++){
                        MyOutputFile.write(paint.get(j));
                    }
                    paint.clear();
                    i=0;
                    column=0;
                    if(d==1){
                        if(row==1){
                            row=0;
                        }else{
                            row=1;
                        }
                    }else if(d==2){
                        if(row==3){
                            row=0;
                        }else{
                            row++;
                        }
                    }
                }
            }
        }catch(IOException ex){
            System.out.println("The size of file does not match!");
        }
    }
    
    static void writeBytes(int value,int matrix){
        if(value>matrix){
            paint.add(255);
        }else{
            paint.add(0);
        }
    }
}
