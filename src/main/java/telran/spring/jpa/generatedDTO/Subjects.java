package telran.spring.jpa.generatedDTO;

public class Subjects {
    public static final String subjects[] = {"JS", "HTML", "CSS", "TypeScript", "React", "Angular","Java", "Spring", "Java Advanced", "Data bases"};

    public static String getSubject(int index){
        return subjects[index];
    }
}
