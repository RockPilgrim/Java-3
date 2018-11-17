import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Testing {

    public static final String FILE_1 = "Lesson_3_resources/1.txt";
    public static final String FILE_2 = "Lesson_3_resources/2.txt";
    public static final String FILE_3 = "Lesson_3_resources/3.txt";
    public static final String FILE_4 = "Lesson_3_resources/4.txt";
    public static final String FILE_5 = "Lesson_3_resources/5.txt";
    private FileInputStream inS = null;
//    private FileOutputStream outS = null;

    public Testing() {
        ArrayList<InputStream> arr = new ArrayList<>();

        /////////////////////////HW2////////////////////////////
        try {
            arr.add(new FileInputStream(FILE_1));
            arr.add(new FileInputStream(FILE_2));
            arr.add(new FileInputStream(FILE_3));
            arr.add(new FileInputStream(FILE_4));
            arr.add(new FileInputStream(FILE_5));
            sewTogether(arr);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ///////////////////////////HW1////////////////////////////
        writeRead(new File("Lesson_3_resources/123.txt"));
        readWrite(new File("Lesson_3_resources/123.txt"));
        //////////////////////////////////////////////////////////
    }

    private void sewTogether(ArrayList<InputStream> arr){
        SequenceInputStream inputStream = new SequenceInputStream(Collections.enumeration(arr));
        try (FileOutputStream outS = new FileOutputStream("Lesson_3_resources/5_in_1.txt")) {
            byte[] bytes=new byte[20];
            int x;
            while ((x=inputStream.read(bytes))!=-1){
                outS.write(new String (bytes,0,x,"UTF-8").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRead(File file){
        try(FileOutputStream outS=new FileOutputStream(file.getPath())) {
//            long time=System.currentTimeMillis();
            StringBuffer words=new StringBuffer("Start");
            for (int i = 0; i < 50-2; i++) {
                words.append("\nHello");
            }
            words.append("\nend ");
            String allText=words.toString();
            byte[] byteLine=allText.getBytes();
            outS.write(byteLine);
//            System.out.println(System.currentTimeMillis() - time);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readWrite(File file){
        try (FileInputStream inS=new FileInputStream(file.getPath())){
            int x;
            while ((x = inS.read()) != -1) {
                System.out.print((char)x);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Testing testing = new Testing();
    }
}
