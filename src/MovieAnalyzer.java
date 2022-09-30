import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieAnalyzer {
    static ArrayList<String> dataset = new ArrayList<>();
    static ArrayList<Movie> movies = new ArrayList<>();

    public MovieAnalyzer(String dataset_path) throws IOException {;
        // 创建 reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataset_path))) {
            // 按行读取
            String line;
            boolean flag = false;
            while ((line = br.readLine()) != null) {
                if (!flag) {
                    flag = true;
                    continue;
                }
                dataset.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String str : dataset) {
            String[] temp = str.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
            Movie movie = new Movie(temp);
            movies.add(movie);
        }

    }

    public static void main(String[] args) throws IOException {
        MovieAnalyzer movieAnalyzer = new MovieAnalyzer("E:\\OneDrive - 南方科技大学\\大学\\4 大三上\\2 课程\\4 Java2\\3 assignment\\Assignment1\\resources\\imdb_top_500.csv");
        System.out.println(dataset.size());

        Map<Integer, Integer> movies_count_by_year = getMovieCountByYear();
        System.out.println(movies_count_by_year);

        Map<String, Integer> movies_count_by_genre = getMovieCountByGenre();
        System.out.println(movies_count_by_genre);
    }

    public static Map<Integer, Integer> getMovieCountByYear() {
        Stream<Movie> movie_stream = movies.stream();
        Map<Integer, Long> temp = movie_stream.collect(Collectors.groupingBy(Movie::getReleased_Year, Collectors.counting()));
        Map<Integer, Integer> result = new TreeMap<>((o1, o2) -> o2 - o1);
        temp.entrySet().stream().forEachOrdered(x -> result.put(x.getKey(), x.getValue().intValue()));
        return result;
    }

    public static Map<String, Integer> getMovieCountByGenre() {
        Stream<Movie> movie_stream = movies.stream();
        Map<String, Long> temp = movie_stream.collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        List<Map.Entry<String, Long>> list = new ArrayList<>(temp.entrySet());
        Collections.sort(list, (o1, o2) -> {
            if (o2.getValue().intValue() > o1.getValue().intValue())
                return 1;
            else if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else
                return -1;
        });
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Long> map : list) {
            result.put(map.getKey(), map.getValue().intValue());
        }
        return result;
    }

    static class Movie {
        private String Series_Title, Certificate, Runtime, Genre, Overview;
        private String Director, Star1, Star2, Star3, Star4, Gross;
        private int Released_Year, Meta_score, No_of_Votes;
        private double IMDB_Rating;


        public Movie(String[] strings) {
            Series_Title = strings[1];
            Released_Year = Integer.parseInt(strings[2]);
            Certificate = strings[3];
            Runtime = strings[4];
            Genre = strings[5];
            IMDB_Rating = Double.parseDouble(strings[6]);
            Overview = strings[7];
//            Meta_score = Integer.parseInt(strings[8]);
            Director = strings[9];
            Star1 = strings[10];
            Star2 = strings[11];
            Star3 = strings[12];
            Star4 = strings[13];
            No_of_Votes = Integer.parseInt(strings[14]);
            Gross = strings[15];
        }

        public int getReleased_Year() {
            return Released_Year;
        }

        public int getMeta_score() {
            return Meta_score;
        }

        public void setMeta_score(String str) {
            Meta_score = Integer.parseInt(str);
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }
    }
}