import java.io.*;
import java.util.*;

public class Dithering {
    static int[][] D1 = {{0,128},{192,64}};
    static int[][] D2 = {{0,128,32,160},{192,64,224,96},{48,176,16,144},{240,112,208,80}};
    static ArrayList<Integer> paint = new ArrayList<Integer>();
    public static void main(String[] args) {
        try{
            int d=1; //type
            String file="lena.raw";
            String outputfile=file.substring(0, file.length()-4)+"(D"+d+").raw";
            int width=123;
            FileOutputStream MyOutputFile=new FileOutputStream(outputfile);
            FileInputStream MyInputFile=new FileInputStream(file);
            Dithering(width,d,MyOutputFile,MyInputFile);
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
    }
    
    static void Dithering(int width,int d,FileOutputStream MyOutputFile,FileInputStream MyInputFile) throws IOException{
        int value;
        int i=0;
        int column=0;
        int row=0;
        while((value=MyInputFile.read())!=-1){
            i++;
            if(i>width-1){
                for(int j=0;j<paint.size();j++){
                    MyOutputFile.write(paint.get(j));
                }
                paint.clear();
                i=0;
                column=0;
                if(d==1){
                    if(row==0){
                        row=1;
                    }else if(row==1){
                        row=0;
                    }
                }else if(d==2){
                    if(row==0){
                        row=1;
                    }else if(row==1){
                        row=2;
                    }else if(row==2){
                        row=3;
                    }else if(row==3){
                        row=0;
                    }
                }
            }else{
                writeBytes(MyOutputFile,value,D2[row][column]);
                if(d==1){
                    if(column==0){
                        column=1;
                    }else if(column==1){
                        column=0;
                    }
                }else if(d==2){
                    if(column==0){
                        column=1;
                    }else if(column==1){
                        column=2;
                    }else if(column==2){
                        column=3;
                    }else if(column==3){
                        column=0;
                    }
                }
            }
        }
    }
    
    static void writeBytes(FileOutputStream MyOutputFile,int value,int matrix){
        if(value>matrix){
            paint.add(255);
        }else{
            paint.add(0);
        }
    }
}
