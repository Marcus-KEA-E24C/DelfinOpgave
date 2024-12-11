public class TrainingResult {

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
    return "Bedste træningstid for den valgt svømmer: \n Disciplin " + discipline + " , tid: " + bestTrainingTimeSeconds + ":" + bestTrainingTimeCentiSeconds + ", dato: " + date;
}


}