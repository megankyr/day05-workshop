import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final int COL_NAME = 1;
    public static final int COL_MAXTIME = 7;
    public static final int COL_MINTIME = 6;
    public static final String[] LABELS = { "30 - 60 mins", "60 - 120 mins", "120 - 180 mins", "More than 180 mins" };

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Please input file path in your command line argument");
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            Map<Integer, List<BoardGames>> classified = bufferedReader.lines()
                    .skip(1)
                    .map(row -> row.trim().split(","))
                    .map(fields -> new BoardGames(fields[COL_NAME], Integer.parseInt(fields[COL_MAXTIME]),
                            Integer.parseInt(fields[COL_MINTIME])))
                    .collect(Collectors.groupingBy(BoardGames -> {
                        int duration = BoardGames.getDuration();
                        if ((duration >= 30) && (duration < 60))
                            return 0;
                        else if ((duration >= 60) && (duration < 120))
                            return 1;
                        else if ((duration >= 120) && (duration < 180))
                            return 2;
                        else
                            return 3;
                    }));

            for (int i = 0; i < LABELS.length; i++) {
                System.out.printf("%s\n", LABELS[i]);
                for (BoardGames boardGame : classified.get(i)) {
                    System.out.printf("\t%s\n", boardGame.getName());
                }
            }

        }
    }
}