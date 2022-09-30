import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        String str = "\"https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg\",The Shawshank Redemption,1994,A,142 min,Drama,9.3,\"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\",80,Frank Darabont,Tim Robbins,Morgan Freeman,Bob Gunton,William Sadler,2343110,\"28,341,469\"\n";
        String str = "\"290,475,067\"";
        str = str.substring(1,str.length()-1);
        String[] temp = str.trim().split(",");
        System.out.println(str);
        str = "";
        for (String s : temp){
            str += s;
            System.out.println(s);
        }
        System.out.println(str);
        long ans =Long.parseLong(str);
        System.out.println(ans);

//        str = str.substring(0, str.length()-4);
//        int runtime = Integer.parseInt(str);
//        System.out.println(runtime);

//        String[] temp = str.split(",");
////        String[] temp = str.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);
//        for (String s : temp)
//            System.out.println(s);
//        int year = Integer.parseInt(temp[2]);
//        System.out.println(year);
//        String str = "\"Mystery, Sci-Fi, Thriller\"", str1 = "Horror";
//        System.out.println(str.compareTo(str1));

    }
}
