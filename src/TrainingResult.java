import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TrainingResult {

 private static List<TrainingResult> trainingResults = new ArrayList<>();

    private String discipline;
    private int bestTrainingTimeSeconds;
    private int bestTrainingTimeCentiSeconds;
    private String date;

    public TrainingResult(String discipline, int bestTrainingTimeSeconds, int bestTrainingTimeCentiSeconds, String date) {
        if (!Member.SwimDisciplineUtil.VALID_DISCIPLINES.contains(discipline.toLowerCase())) {
            throw new IllegalArgumentException("Ugyldig disciplin indtastet: '" + discipline + "'");
        }
        this.discipline = discipline;
        this.bestTrainingTimeSeconds = bestTrainingTimeSeconds;
        this.bestTrainingTimeCentiSeconds = bestTrainingTimeCentiSeconds;
        this.date = date;

    }


 public static TrainingResult enterTrainingResult() {
 Scanner scanner = new Scanner(System.in);

 String discipline = "";
 boolean validDiscipline = false;

 while (!validDiscipline) {
 System.out.println("Indtast disciplin for træningsresultat (muligheder: backstroke, breaststroke, butterfly, crawl):");
 discipline = scanner.nextLine().toLowerCase();
 if (Member.SwimDisciplineUtil.VALID_DISCIPLINES.contains(discipline)) {
 validDiscipline = true;
 } else {
 System.out.println("Den indtastede disciplin kunne ikke genkendes");
 }
 }


 System.out.println("Bedste træningstid gemmes i sekunder og centisekunder. \n");

 System.out.println("Indtast bedste træningstid i hele sekunder");
 int bestTrainingTimeSeconds = Integer.parseInt(scanner.nextLine());


 System.out.println("Indtast bedste træningstid i hele centisekunder");
 int bestTrainingTimeCentiSeconds = Integer.parseInt(scanner.nextLine());

 System.out.println("Indtast dato for bedste træningstid (format: dd/mm/åå");
 String date = scanner.nextLine();

 return new TrainingResult(discipline, bestTrainingTimeSeconds, bestTrainingTimeCentiSeconds, date);



 }




 public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getBestTrainingTimeSeconds() {
        return bestTrainingTimeSeconds;

    }

    public void setBestTrainingTimeSeconds(int bestTime) {
        this.bestTrainingTimeSeconds = bestTime;
    }


    public int getBestTrainingTimeCentiSeconds() {
        return bestTrainingTimeCentiSeconds;

    }

    public void setBestTrainingTimeCentiSeconds(int bestTime) {
        this.bestTrainingTimeCentiSeconds = bestTime;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


 @Override
 public String toString()
 {
 return "Bedste træningstid for den valgt svømmer: \nDisciplin " + discipline + " , tid: " + bestTrainingTimeSeconds + ":" + bestTrainingTimeCentiSeconds + ", dato: " + date;
 }


 }


