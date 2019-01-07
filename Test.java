package filesprocessing;

/**
 * Created by Yarden Tal on 16/06/2017.
 */
public class Test {


    public static void main(String[] args){
        String a = "av {voud";
        a= a.replaceAll("\\{","");

        String s = "gg = 55";
        String[] ss = s.split("=");
        for (String sss:ss
             ) {
            System.out.println(sss);

        }
    }
}
