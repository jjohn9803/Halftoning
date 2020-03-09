import java.io.*;
import java.util.ArrayList;

public class ReadFile {
    public static int dataStart=0;
    public static void main(String[] args) {
        readTif("yoda.tif");
        readTif("Imgpro.tif");
        //readTif("lena.tif");
    }
    
    static void readTif(String file){
        try{
            FileInputStream MyInputFile=new FileInputStream(file);
            PrintWriter out = new PrintWriter(file.substring(0, file.length()-4)+".txt");
            String output="";
            output+="Filename: "+file+"\n";
            //System.out.println("Filename: "+file);
            output+="-----------------------------Header Info-----------------------------\n";
            //System.out.println("-----------------------------Header Info-----------------------------");
            String byteOrder=String.format("%02X", (0xFF & MyInputFile.read()));
            byteOrder+=String.format("%02X", (0xFF & MyInputFile.read()));
            String version=readByte(MyInputFile,2,byteOrder);
            int offset=Integer.parseInt(readByte(MyInputFile,4,byteOrder),16);
            int de=Integer.parseInt(readByte(MyInputFile,2,byteOrder),16);
            output+="Byte Order:\t\t\t"+byteOrder+"\n";
            //System.out.println("Byte Order:\t\t\t"+byteOrder);
            output+="Version:\t\t\t"+version+"\n";
            //System.out.println("Version:\t\t\t"+version);
            output+="Offset:\t\t\t\t"+offset+"\n";
            //System.out.println("Offset:\t\t\t\t"+offset);
            output+="Number of Directory Entry:\t"+de+"\n";
            //System.out.println("Number of Directory Entry:\t"+de);
            output+="-----------------------------Data  Entry-----------------------------\n";
            //System.out.println("-----------------------------Data  Entry-----------------------------");
            output+="\tTag\t\t\t\tType\t\tCount\tValue\n";
            //System.out.println("\tTag\t\t\t\tType\t\tCount\tValue");
            output+="---------------------------------------------------------------------\n";
            //System.out.println("---------------------------------------------------------------------");
            for(int i=0;i<de;i++){
                String ReadData=readData(MyInputFile,byteOrder);
                if(ReadData.isEmpty()==false){
                    output+=ReadData+"\n";
                    //System.out.println(ReadData);
                }
            }
            MyInputFile.close();
            MyInputFile=new FileInputStream(file);
            MyInputFile.skip(dataStart);
            int value;
            int i=0;
            output+="\n-----------------------------Image  Data-----------------------------\n\t\t";
            //System.out.print("\n-----------------------------Image  Data-----------------------------\n\t\t");
            while((value=MyInputFile.read())!=-1){
                output+=String.format("%02X", (0xFF & value));
                //System.out.print(String.format("%02X", (0xFF & value)));
                if(i>=15){
                    output+="\n\t\t";
                    //System.out.print("\n\t\t");
                    i=0;
                }else{
                    if(i%2!=0){
                        output+=" ";
                        //System.out.print(" ");
                    }
                    i++;
                }
            }
            System.out.println(output);
            System.out.println("\n");
            out.print(output);
            MyInputFile.close();
        }catch(IOException ex){
            System.out.println("file input error!");
        }
    }
    
    static String readData(FileInputStream FIS,String ByteOrder){
        String tag=readByte(FIS,2,ByteOrder);
        String type=readByte(FIS,2,ByteOrder);
        String length=readByte(FIS,4,ByteOrder);
        int tagInt=Integer.parseInt(tag,16);
        int typeInt=Integer.parseInt(type,16);
        int lengthInt=Integer.parseInt(length,16);
        String value;
        if(typeInt==3){
            value=readByte(FIS,2,ByteOrder);
            String temp=readByte(FIS,2,ByteOrder);
            if(temp.equals("0000")==false){
                value=temp+value;
            }
        }else{
            value=readByte(FIS,4,ByteOrder);
        }
        int valueInt=Integer.parseInt(value,16);
        if(tag.equals("0111")){
            dataStart=valueInt;
        }
        String ReadTag=readTag(tag,tagInt);
        if(ReadTag.equals("\t\t\t")==false){
            return ReadTag+readType(type,typeInt)+"\t"+lengthInt+"\t"+valueInt;
        }else{
            return "";
        }
    }
    
    static String readByte(FileInputStream FIS,int size,String ByteOrder){
        String msg="";
        try{
            ArrayList<String> Data = new ArrayList<String>();
            for(int i=0;i<size;i++){
                String data=String.format("%02X", (0xFF & FIS.read()));
                Data.add(data);
            }
            boolean skip=true;
            if(ByteOrder.equals("4949")){
                for(int i=size-1;i>=0;i--){
                    if(Data.get(i).equals("00")){
                        if(skip==false){
                            msg+=Data.get(i);
                        }
                    }else{
                        msg+=Data.get(i);
                        skip=false;
                    }
                }
            }else if(ByteOrder.equals("4D4D")){
                for(int i=0;i<size;i++){
                    if(Data.get(i).equals("00")){
                        if(skip==false){
                            msg+=Data.get(i);
                        }
                    }else{
                        msg+=Data.get(i);
                        skip=false;
                    }
                }
            }
        }catch(IOException ex){
            System.out.println("file input error!");
        }
        if(msg.equals("")){
            msg="0";
        }
        return msg;
    }
    
    static String readTag(String tag,int tagInt){
        if(tagInt==254){
            tag+=" (NewSubfileType)";
        }else if(tagInt==255){
            tag+=" (SubFileType)";
        }else if(tagInt==256){
            tag+=" (ImageWidth)";
        }else if(tagInt==257){
            tag+=" (ImageLength)";
        }else if(tagInt==258){
            tag+=" (BitsPerSample)";
        }else if(tagInt==259){
            tag+=" (Compression)";
        }else if(tagInt==262){
            tag+=" (PhotometricInterpretation)";
        }else if(tagInt==273){
            tag+=" (StripOffsets)";
        }else if(tagInt==277){
            tag+=" (SamplesPerPixel)";
        }else if(tagInt==278){
            tag+=" (RowsPerStrip)";
        }else if(tagInt==279){
            tag+=" (StripByteCounts)";
        }else if(tagInt==282){
            tag+=" (XResolution)";
        }else if(tagInt==283){
            tag+=" (YResolution)";
        }else{
            tag="";
        }
        if(tagInt==262){
            tag+="\t";
        }else{
            tag+="\t\t\t";
        }
        return tag;
    }
    
    static String readType(String type,int typeInt){
        type=""+typeInt;
        if(typeInt==1){
            type+=" (BYTE)";
        }else if(typeInt==2){
            type+=" (ASCII)";
        }else if(typeInt==3){
            type+=" (SHORT)";
        }else if(typeInt==4){
            type+=" (LONG)";
        }else if(typeInt==5){
            type+=" (RATIONAL)";
        }
        return type;
    }
    
}
