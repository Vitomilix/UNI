package at.tugraz.ist.swe.lang;

public class Questions extends SimpleTestActivity {

    public String myQuestions[] = {

            "Foot",
            "Room",
            "Light",
            "Eight",
            "Mirror",
            "Jacket"

    };


    private String myChoices[][] = {

            {"Raum","Fuß","Licht","Spiegel"},
            {"Acht","Licht","Fuß","Raum"},
            {"Spiegel","Licht","Raum","Jacke"},
            {"Acht","Raum","Spiegel","Fuß"},
            {"Licht","Fuß","Jacke","Spiegel"},
            {"Jacke","Acht","Spiegel","Raum"},
            {"Jacke","Acht","Spiegel","Tafel"},

    };

    private String myCorrectAnswers[] = {"Fuß", "Raum", "Licht", "Acht","Spiegel", "Jacke", "Tafel"};


    public String getQuestion (int a){

        String question = myQuestions[a];
        return question;

    }

    public String[] getItems (int a){
        String[] items = myChoices[a];
        return items;
    }


    public String getCorrectAnswer(int a){

        String answer = myCorrectAnswers[a];

        return answer;

    }

}
