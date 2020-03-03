import java.io.*;
public class WriteFile {
    public static void main(String[] args) {
        // File MyOutputFile
        try{
            FileOutputStream MyOutputFile=new FileOutputStream("myFile.txt");
            MyOutputFile.write(1);
            MyOutputFile.write(2);
            MyOutputFile.write('h');
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
        
    }
}
