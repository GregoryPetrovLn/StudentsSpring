package telran.spring.jpa.dto;

public class StudentDto {
    public int stid;
    public String name;

    public StudentDto() {
    }

    public StudentDto(int stid, String name) {
        this.stid = stid;
        this.name = name;
    }

}
