public class WarmUp {
    public static void main(String args[]){
        //System.out.println("hello");
//        boolean check=true;
//        if(!check){
//            System.out.println("true");
//        }else{
//            System.out.println("false");
//        }
//        int i=0;
//        switch(i){
//            case 0:
//                System.out.println("something");
//        }
        //boolean isTrue=true;//initial
//        int i=0;
//        while(i<5){//comparison
//          System.out.println("ok");
//          //isTrue=false;//altering value
//          i++;
//        }
//        do{
//            System.out.println("1");
//            System.out.println("2");
//            System.out.println("3");
//        }while(false);
        for (int j = 5; j >0; j--) {
            for (int i = 1; i <= j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        int[]array=new int[5];
        int[]array2={1,2,3,4,5};
        int[][]array3=new int[2][2];
        int[][]array4={{1,2},{3,4},{5,6}};
        
        System.out.println(array4[0][1]);
        
    }
}