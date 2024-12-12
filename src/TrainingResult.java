import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TrainingResult {
    private static MemberList memberList  = new MemberList();;

 private static List<TrainingResult> trainingResults = new ArrayList<>();
    private static List<TrainingResult> juniorBackstroke = new ArrayList<>();
    private static List<TrainingResult> juniorBreaststroke = new ArrayList<>();
    private static List<TrainingResult> juniorButterfly = new ArrayList<>();
    private static List<TrainingResult> juniorCrawl = new ArrayList<>();
    private static List<TrainingResult> seniorBackstroke = new ArrayList<>();
    private static List<TrainingResult> seniorBreaststroke = new ArrayList<>();
    private static List<TrainingResult> seniorButterfly = new ArrayList<>();
    private static List<TrainingResult> seniorCrawl = new ArrayList<>();


 private Member member;
    private String discipline;
    private int bestTrainingTimeSeconds;
    private int bestTrainingTimeCentiSeconds;
    private String date;
    private String mpSwimmer;


    public TrainingResult(Member member, String discipline, int bestTrainingTimeSeconds, int bestTrainingTimeCentiSeconds, String date) {
        if (!Member.SwimDisciplineUtil.VALID_DISCIPLINES.contains(discipline.toLowerCase())) {
            throw new IllegalArgumentException("Ugyldig disciplin indtastet: '" + discipline + "'");
        }



        this.member = member;
        this.discipline = discipline;
        this.bestTrainingTimeSeconds = bestTrainingTimeSeconds;
        this.bestTrainingTimeCentiSeconds = bestTrainingTimeCentiSeconds;
        this.date = date;
        trainingResults.add(this);

    }


    public static void printTrainingResults() {
        if (trainingResults.isEmpty()) {
            System.out.println("Der er ingen træningsresultater at vise.");
        } else {
            System.out.println("Træningsresultater: \n");
            for (TrainingResult result : trainingResults) {
                System.out.println(result);
            }
        }

    }




    public static TrainingResult enterTrainingResult() {
 Scanner scanner = new Scanner(System.in);

 System.out.println("Indtast svømmerens navn");
 String memberName = scanner.nextLine();

 Member member = memberList.searchMember(memberName);





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

        TrainingResult newTrainingResult = new TrainingResult(member, discipline, bestTrainingTimeSeconds, bestTrainingTimeCentiSeconds, date);
        if (member.getAgeGroup().equals("JUNIOR")) {
            switch (discipline) {
                case "backstroke":
                    juniorBackstroke.add(newTrainingResult);
                    break;
                case "breaststroke":
                    juniorBreaststroke.add(newTrainingResult);
                    break;
                case "butterfly":
                    juniorButterfly.add(newTrainingResult);
                    break;
                case "crawl":
                    juniorCrawl.add(newTrainingResult);
                    break;
                default:
                    System.out.println("Ugyldig disciplin");
                    
                    /** den er vidst nok ok nu, skal bare lige finde ud af hvor jeg har en fucked curly bracket**/


        } else if ((member.getAgeGroup().equals("SENIOR"))) {
                switch (discipline) {
                    case "backstroke":
                        seniorBackstroke.add(newTrainingResult);
                        break;
                    case "breaststroke":
                        seniorBreaststroke.add(newTrainingResult);
                        break;
                    case "butterfly":
                        seniorButterfly.add(newTrainingResult);
                        break;
                    case "crawl":
                        seniorCrawl.add(newTrainingResult);
                        break;
                    default:
                        System.out.println("Ugyldig disciplin");


                }  } else if ((member.getAgeGroup().equals("PENSIONIST"))) {
                switch (discipline) {
                    case "backstroke":
                        seniorBackstroke.add(newTrainingResult);
                        break;
                    case "breaststroke":
                        seniorBreaststroke.add(newTrainingResult);
                        break;
                    case "butterfly":
                        seniorButterfly.add(newTrainingResult);
                        break;
                    case "crawl":
                        seniorCrawl.add(newTrainingResult);
                        break;
                    default:
                        System.out.println("Ugyldig disciplin");


                }
            }

        }

        return new TrainingResult(member, discipline, bestTrainingTimeSeconds, bestTrainingTimeCentiSeconds, date);



 }


public Member getMember() {
return member;
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
 return "Bedste træningstid for den valgte svømmer: \n Svømmer: " + member.getName() + " Disciplin " + discipline + " , tid: " + bestTrainingTimeSeconds + ":" + bestTrainingTimeCentiSeconds + ", dato: " + date;
 }


 }


