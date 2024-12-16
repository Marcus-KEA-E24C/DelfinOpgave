import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TrainingTop5 {

    // Indre klasse til at holde information om en svømmeres træningstid
    public static class TrainCompResult {
        String swimmerName;
        String discipline;
        double time; // Tid i sekunder,hundrededele

        public TrainCompResult(String swimmerName, String discipline, double time) {
            this.swimmerName = swimmerName;
            this.discipline = discipline;
            this.time = time;
        }
    }

    // Metode til at læse data fra træningsfilerne (compswimmerresults.txt) og returnere en liste af resultater
    public List<TrainCompResult> readTrainCompResults(String filename) {
        List<TrainCompResult> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true; // Flag for at springe overskriftslinjen over

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Spring første linje over
                    continue;
                }

                // Split linjen baseret på separatoren ";"
                String[] parts = line.split(";");
                if (parts.length < 3) {
                    System.out.println("Ugyldigt format i linjen: " + line);
                    continue;
                }

                try {
                    String swimmerName = parts[0].trim();
                    String discipline = parts[1].trim();
                    double time = Double.parseDouble(parts[2].trim());

                    results.add(new TrainCompResult(swimmerName, discipline, time));
                } catch (NumberFormatException e) {
                    System.out.println("Fejl i format for tid i linjen: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return results;
    }

    // Metode til at finde top 5 træningstider for hver disciplin
    public void displayTrainingTopFive(String filename) {
        List<TrainCompResult> results = readTrainCompResults(filename);

        if (results.isEmpty()) {
            System.out.println("Ingen data tilgængelig i filen.");
            return;
        }

        // Grupper resultaterne efter disciplin
        Map<String, List<TrainCompResult>> groupedResults = results.stream()
                .collect(Collectors.groupingBy(result -> result.discipline));

        for (String discipline : groupedResults.keySet()) {
            System.out.println("\nDisciplin: " + discipline);

            List<TrainCompResult> topResults = groupedResults.get(discipline)
                    .stream()
                    .sorted(Comparator.comparingDouble(result -> result.time))
                    .limit(5)
                    .collect(Collectors.toList());

            if (topResults.isEmpty()) {
                System.out.println("Ingen resultater tilgængelige.");
            } else {
                for (int i = 0; i < topResults.size(); i++) {
                    TrainCompResult result = topResults.get(i);
                    System.out.printf("%d. %s - Tid: %.2f sek.%n", i + 1, result.swimmerName, result.time);
                }
            }
        }
    }
}