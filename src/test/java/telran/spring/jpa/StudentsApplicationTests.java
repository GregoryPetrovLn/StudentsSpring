package telran.spring.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import telran.spring.jpa.dto.supported_interfaces.StudentMarksCount;
import telran.spring.jpa.service.interfaces.Students;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class StudentsApplicationTests {
    public static final String CLASS_PATH_SQL = "classpath:fillTables.sql";
    @Autowired
    Students students;
    @Autowired
    MockMvc mockMvc;

    //DONT FORGET TO REMOVE ANNOTATION DISABLED

    @Nested
    @DisplayName("DELETE")
    class Delete {
        @Test
        @Sql(scripts = {CLASS_PATH_SQL})
        void deleteMarks() throws Exception {
            int status = getStatusOnDelete("/marks/Java?name=Moshe");
            assertEquals(200, status);

            status = getStatusOnDelete("/marks/React?name=Moshe");
            assertEquals(200, status);

//        List<Integer> marks = students.getStudentSubjectMarks("Moshe", "Java");
//        System.out.println("size ========== " + marks.size());

            withoutMoshe();
        }

        @Test
        @Sql(scripts = {CLASS_PATH_SQL})
        void deleteStudent() {
            students.deleteStudent("Moshe");
            withoutMoshe();
        }
    }


    @Nested
    @Sql(scripts = {CLASS_PATH_SQL})
    @DisplayName("GET")
    class Get {
        @Test
        void bestStudents() throws Exception {
            int status = getStatus("/students/best");
            List<String> bestStudents = students.bestStudents();
            assertEquals(1, bestStudents.size());
            assertEquals("Moshe", bestStudents.get(0));
            assertEquals(200, status);
        }


        @Test
        void topBestStudents() throws Exception {
            int status = getStatus("/students/best?n_students=2");
            List<String> bestStudents = students.bestStudents(2);
            assertEquals(2, bestStudents.size());
            assertEquals("Moshe", bestStudents.get(0));
            assertEquals("Sara", bestStudents.get(1));
            assertEquals(200, status);
        }

        @Test
        void topBestStudentsSubject() throws Exception {
            int status = getStatus("/students/best?n_students=2&subject=Java");
            List<String> bestStudents = students.bestStudentsSubject(2, "Java");
            assertEquals(200, status);
            assertEquals(2, bestStudents.size());
        }

        @Test
        void bestStudentsSubject() throws Exception {
            List<String> bestStudents = students.bestStudentsSubject("Java");
            int status = getStatus("/students/best?subject=Java");
            assertEquals(200, status);
            assertEquals(1, bestStudents.size());
            assertEquals("Moshe", bestStudents.get(0));
        }

        @Test
        void getTopMarksEncountered() throws Exception {
            int status = getStatus("/marks/widespread/Java?n_marks=2");
            assertEquals(200, status);
        }

        @Test
        void highestSubjectMarks() throws Exception {
            int status = getStatus("/subject/marks/count?name=Moshe&subject=Java");
            assertEquals(200, status);

        }

        @Test
        void getIntervalsMarks() throws Exception {
            int status = getStatus("/marks/distribution?interval=20");
            assertEquals(200, status);
            status = getStatus("/marks/distribution");
            assertEquals(200, status);
        }

        @Test
        void studentsMarksCount() throws Exception {
            int status = getStatus("/students/marks/count");
            List<StudentMarksCount> studentsMarksCount = students.getStudentsMarksCount();
            assertEquals(200, status);
            assertEquals(2, studentsMarksCount.size());

            if (studentsMarksCount.get(0).getName() == "Moshe") {
                assertEquals(3, studentsMarksCount.get(0).getMarksCount());
            }
            if (studentsMarksCount.get(0).getName() == "Sara") {
                assertEquals(1, studentsMarksCount.get(0).getMarksCount());
            }

        }

        @Test
        void getStudentSubjectMarks() throws Exception {
            int status = getStatus("/subject/marks/count?name=Moshe&subject=Java");
            assertEquals(200, status);
        }

        @Test
        void subjectsMarksHighest() throws Exception {
            int status = getStatus("/subject/marks/highest");
            assertEquals(200, status);

        }

        @Test
        void topSubjectsMarksHighest() throws Exception {
            int status = getStatus("/subject/marks/highest?n_subjects=2");
            assertEquals(200, status);

        }

        @Test
        void getAvgMark() throws Exception {
            int status = getStatus("/marks/get/avg?name=Moshe&subject=Java");
            int avgMark = students.getAvgMark("Moshe", "Java");
            assertEquals(200, status);
            assertEquals(95, avgMark);
        }

    }


    @Nested
    @DisplayName("UPDATE")
    class Update {
        @Sql(scripts = {CLASS_PATH_SQL})
        @Test
        void setAvgMark() throws Exception {
            int status = getStatus("/marks/set/avg?name=Moshe&subject=Java");
            assertEquals(200, status);
        }
    }

    //=============================================================
    //=============================================================
    //=============================================================
    //=============================================================
    //=============================================================


    private int getStatus(String request) throws Exception {
        int status = mockMvc.perform(MockMvcRequestBuilders.get(request))
                .andReturn().getResponse().getStatus();

        return status;
    }

    private int getStatusOnDelete(String request) throws Exception {
        int status = mockMvc.perform(MockMvcRequestBuilders.delete(request))
                .andReturn().getResponse().getStatus();

        return status;
    }

    private void withoutMoshe() {
        List<String> bestStudents = students.bestStudents(2);
        assertEquals(1, bestStudents.size());
        assertEquals("Sara", bestStudents.get(0));
    }


}
