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

    public MovieAnalyzer(String dataset_path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataset_path))) {
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


    public Map<Integer, Integer> getMovieCountByYear() {
        Stream<Movie> movie_stream = movies.stream();
        Map<Integer, Long> temp = movie_stream.collect(Collectors
                .groupingBy(Movie::getReleased_Year, Collectors.counting()));
        Map<Integer, Integer> result = new TreeMap<>((o1, o2) -> o2 - o1);
        temp.entrySet().stream().forEachOrdered(x ->
                result.put(x.getKey(), x.getValue().intValue()));
        return result;
    }

    public Map<String, Integer> getMovieCountByGenre() {
        Map<String, Integer> temp = new HashMap<>();
        for (Movie m : movies) {
            String genre = m.Genre;
            if (genre.contains("\""))
                genre = genre.substring(1, genre.length() - 1);
            String[] strings = genre.trim().split(",");
            for (String s : strings) {
                s = s.trim();
                if (temp.containsKey(s))
                    temp.put(s, temp.get(s) + 1);
                else
                    temp.put(s, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(temp.entrySet());
        Collections.sort(list, (o1, o2) -> {
            if (o2.getValue().intValue() > o1.getValue().intValue())
                return 1;
            else if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else
                return -1;
        });

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> map : list) {
            result.put(map.getKey(), map.getValue().intValue());
        }
        return result;
    }

    public Map<List<String>, Integer> getCoStarCount() {
        Map<List<String>, Integer> temp = new HashMap<>();
        for (Movie m : movies) {
            String star1 = m.Star1, star2 = m.Star2, star3 = m.Star3, star4 = m.Star4;
            List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>(),
                    list4 = new ArrayList<>(), list5 = new ArrayList<>(), list6 = new ArrayList<>();
            list1.add(star1);
            list1.add(star2);
            list2.add(star1);
            list2.add(star3);
            list3.add(star1);
            list3.add(star4);
            list4.add(star2);
            list4.add(star3);
            list5.add(star2);
            list5.add(star4);
            list6.add(star3);
            list6.add(star4);
            Collections.sort(list1);
            Collections.sort(list2);
            Collections.sort(list3);
            Collections.sort(list4);
            Collections.sort(list5);
            Collections.sort(list6);
            map_put(temp, list1);
            map_put(temp, list2);
            map_put(temp, list3);
            map_put(temp, list4);
            map_put(temp, list5);
            map_put(temp, list6);
        }
        List<Map.Entry<List<String>, Integer>> list = new ArrayList<>(temp.entrySet());
        Collections.sort(list, ((o1, o2) -> o2.getValue() - o1.getValue()));
        Map<List<String>, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<List<String>, Integer> map : list) {
            result.put(map.getKey(), map.getValue());
        }
        return result;
    }

    public static void map_put(Map<List<String>, Integer> map, List<String> list) {
        if (!map.containsKey(list))
            map.put(list, 1);
        else
            map.put(list, map.get(list) + 1);
    }

    public List<String> getTopMovies(int top_k, String by) {
        List<String> result = new ArrayList<>();
        ArrayList<Movie> temp = new ArrayList<>(movies);
        if (by.equals("runtime")) {
            Collections.sort(temp, (o1, o2) -> {
                if (o2.Runtime > o1.Runtime)
                    return 1;
                else if (o1.Runtime == o2.Runtime)
                    return o1.Series_Title.compareTo(o2.Series_Title);
                else
                    return -1;
            });
        } else if (by.equals("overview")) {
            Collections.sort(temp, (o1, o2) -> {
                if (o2.Overview.length() > o1.Overview.length()) {
                    return 1;
                } else if (o1.Overview.length() == o2.Overview.length()) {
                    return o1.Series_Title.compareTo(o2.Series_Title);
                } else {
                    return -1;
                }
            });
        }
        for (int i = 0; i < top_k; i++) {
            Movie movie = temp.get(i);
            String name = movie.Series_Title;
            result.add(name);
        }
        return result;
    }

    public List<String> getTopStars(int top_k, String by) {
        List<String> result = new ArrayList<>();
        Map<String, Double> star_rating = new LinkedHashMap<>();
        Map<String, Integer> star_count = new LinkedHashMap<>();
        Map<String, Double> star_gross = new LinkedHashMap<>();
        List<Map.Entry<String, Double>> list;
        if (by.equals("rating")) {
            for (Movie movie : movies) {
                String star1 = movie.Star1, star2 = movie.Star2, star3 = movie.Star3, star4 = movie.Star4;
                double rating = movie.IMDB_Rating;
                rating_put(star_rating, star1, rating);
                count_put(star_count, star1);

                rating_put(star_rating, star2, rating);
                count_put(star_count, star2);

                rating_put(star_rating, star3, rating);
                count_put(star_count, star3);

                rating_put(star_rating, star4, rating);
                count_put(star_count, star4);
            }
            list = sort(star_rating, star_count);
            for (int i = 0; i < top_k; i++) {
                Map.Entry entry = list.get(i);
                result.add((String) entry.getKey());
            }
        } else if (by.equals("gross")) {
            for (Movie movie : movies) {
                String star1 = movie.Star1, star2 = movie.Star2, star3 = movie.Star3, star4 = movie.Star4;
                String temp = movie.Gross;
                if (temp.equals("")) {
                    continue;
                }
                temp = temp.substring(1, temp.length() - 1);
                String[] strings = temp.trim().split(",");
                temp = "";
                for (String s : strings) {
                    temp += s;
                }
                Double gross = Double.parseDouble(temp);

                gross_put(star_gross, star1, gross);
                count_put(star_count, star1);

                gross_put(star_gross, star2, gross);
                count_put(star_count, star2);

                gross_put(star_gross, star3, gross);
                count_put(star_count, star3);

                gross_put(star_gross, star4, gross);
                count_put(star_count, star4);
            }
            list = sort(star_gross, star_count);
            for (int i = 0; i < top_k; i++) {
                Map.Entry entry = list.get(i);
                result.add((String) entry.getKey());
            }
        }
        return result;
    }

    public static void rating_put(Map<String, Double> map, String key, Double value) {
        if (!map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, map.get(key) + value);
        }
    }

    public static void gross_put(Map<String, Double> map, String key, Double value) {
        if (!map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, map.get(key) + value);
        }
    }

    public static void count_put(Map<String, Integer> map, String key) {
        if (!map.containsKey(key)) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }
    }

    public static List<Map.Entry<String, Double>> sort(Map<String, Double> map,
                                                       Map<String, Integer> star_count) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, Double> entry : list) {
            String key = entry.getKey();
            int count = star_count.get(key);
            if (count > 1) {
                Double value = entry.getValue();
                value = value / count;
                map.put(key, value);
            }
        }
        List<Map.Entry<String, Double>> list1 = new ArrayList<>(map.entrySet());
        Collections.sort(list1, ((o1, o2) -> {
            if (o2.getValue() > o1.getValue()) {
                return 1;
            } else if (o1.getValue().doubleValue() == o2.getValue().doubleValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return -1;
            }
        }));

        return list1;
    }

    public List<String> searchMovies(String genre, float min_rating, int max_runtime) {
        List<String> result = new ArrayList<>();
        String name = "";
        for (Movie movie : movies) {
            String genres = movie.Genre;
            if (genres.equals(genre) || genres.contains(genre)) {
                double rating = movie.IMDB_Rating;
                int runtime = movie.Runtime;
                if (rating >= min_rating && runtime <= max_runtime) {
                    name = movie.Series_Title;
                    if (name.contains("\"")) {
                        name = name.substring(1, name.length() - 1);
                    }
                    result.add(name);
                }
            }
        }
        Collections.sort(result, String::compareTo);
        return result;
    }

    static class Movie {
        private String Series_Title, Certificate, Genre, Overview;
        private String Director, Star1, Star2, Star3, Star4, Gross;
        private int Released_Year, Meta_score, No_of_Votes, Runtime;
        private float IMDB_Rating;


        public Movie(String[] strings) {
            String name = strings[1];
            if (name.contains("\"")) {
                name = name.substring(1, name.length() - 1);
            }
            Series_Title = name;
            Released_Year = Integer.parseInt(strings[2]);
            Certificate = strings[3];
            Runtime = Integer.parseInt(strings[4].substring(0, strings[4].length() - 4));
            Genre = strings[5];
            IMDB_Rating = Float.parseFloat(strings[6]);
            String overView = strings[7];
            if (overView.charAt(0) == '"') {
                overView = overView.substring(1, overView.length() - 1);
            }
            Overview = overView;
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
    }
}