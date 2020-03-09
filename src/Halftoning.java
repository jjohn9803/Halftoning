import java.util.*;

public class Halftoning {
    public static void main(String[] args) {
        Scanner console=new Scanner(System.in);
        System.out.print("Filename: ");
        String filename=console.next();
        System.out.print("1.Detail\n2.Patterning\n3.Dithering?\nAns:");
        String ans="";
        while(ans.isEmpty()){
            ans=console.next();
            if(ans.equals(1)||ans.equals(2)||ans.equals(3)){}else{
                ans="";
            }
        }
        
    }
}
