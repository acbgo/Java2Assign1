import java.io.IOException;
import java.util.*;

public class test {
    public static void main(String[] args) {
//        String str = "\"https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg\",The Shawshank Redemption,1994,A,142 min,Drama,9.3,\"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\",80,Frank Darabont,Tim Robbins,Morgan Freeman,Bob Gunton,William Sadler,2343110,\"28,341,469\"\n";
//        String str = "\"290,475,067\"";
//        str = str.substring(1,str.length()-1);
//        String[] temp = str.trim().split(",");
//        System.out.println(str);
//        str = "";
//        for (String s : temp){
//            str += s;
//            System.out.println(s);
//        }
//        System.out.println(str);
//        long ans =Long.parseLong(str);
//        System.out.println(ans);

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


//        Map<List<String>,Integer> map = new HashMap<>();
//        List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>();
//        list1.add("a");
//        list2.add("a");
//        map.put(list1,1);
//        System.out.println(map.containsKey(list1));
//        System.out.println(map.containsKey(list2));
//        System.out.println(list1.equals(list2));

        //    public static void main(String[] args) throws IOException {
//        MovieAnalyzer movieAnalyzer = new MovieAnalyzer("E:\\OneDrive - 南方科技大学\\大学\\4 大三上\\2 课程\\4 Java2\\3 assignment\\Assignment1\\resources\\imdb_top_500.csv");
//        List<String> list = getTopMovies(100, "runtime");
//    }

//        List<String> list = new ArrayList<>();
//        list.add("Il buono, il brutto, il cattivo");
//        list.add("Anatomy of a Murder");
//        list.add("Dangal");
//        Collections.sort(list);
//        System.out.println(list);
//
//        String str1 = "Il buono, il brutto, il cattivo",str2 = "Il buono, il brutto, il cattivo";
//        System.out.println(str1.equals(str2));

//        double a = 30.1, b = 30.1;
//        System.out.println(a == b);
//        double c = a/3, d = b/3;
//        System.out.println(c == d);

        String overView = "\"strings[70]\"";
        if (overView.charAt(0) == '"')
            overView = overView.substring(1,overView.length()-1);
        System.out.println(overView);


//        public static void main(String[] args) throws IOException {
//            MovieAnalyzer movieAnalyzer = new MovieAnalyzer("E:\\OneDrive - 南方科技大学\\大学\\4 大三上\\2 课程\\4 Java2\\3 assignment\\Assignment1\\resources\\imdb_top_500.csv");
//            List<String> list = getTopStars(15, "rating");
//            for (String s : list)
//                System.out.println(s);
//        }

    }
}
