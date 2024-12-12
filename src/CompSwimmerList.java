import java.util.List;
import java.util.ArrayList;


public class CompSwimmerList {


    private List<Member> memberList;

    public CompSwimmerList(List<Member> memberList) {
        this.memberList = memberList;


    }

    public void displayCompSwimmers() {

 List<Member> compSwimmerList = new ArrayList<>();


        for (Member member : memberList) {
            if (member.isCompSwimmer()) {
                compSwimmerList.add(member);
            }
        }

if (compSwimmerList.isEmpty()) {
    System.out.println("Der er ikke registreret noget konkurrencesvømmere. \n");
} else {
    for (Member member : memberList) {
        if (member.isCompSwimmer()) {
            System.out.println("Medlem: " + member.getName());
            System.out.println("Aldersgruppe: " + member.getAgeGroup());
            System.out.println("Aktiv: " + (member.isActive() ? "Ja" : "Nej"));
            System.out.println("Konkurrencesvømmer: Ja");

            List<String> swimDisciplines = member.getSwimDisciplines();
            if (swimDisciplines == null)
            {
                System.out.println("ingen aktive svømmediscipliner registreret");
                System.out.println("\n");
            } else {
                System.out.println("Svømmediscipliner: " + String.join(", ", member.getSwimDisciplines()));
                System.out.println("\n");
            }

        }
    }
}


    }
}
