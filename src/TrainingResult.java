public class TrainingResult {

    private String discipline;
    private double bestTrainingTime;
    private String date;

    public TrainingResult(String discipline, double bestTrainingTime, String date) {
        if (!Member.SwimDisciplineUtil.VALID_DISCIPLINES.contains(discipline.toLowerCase())) {
            throw new IllegalArgumentException("Ugyldig disciplin indtastet: '" + discipline + "'");
        }
        this.discipline = discipline;
        this.bestTrainingTime = bestTrainingTime;
        this.date = date;


    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public double getBestTime() {
        return bestTrainingTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTrainingTime = bestTime;
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
    return "Bedste træningstid for den valgt svømmer: \n Disciplin " + discipline + " , tid: " + bestTrainingTime + ", dato: " + date;
}


}