package com.huschle.roster;

import static java.lang.System.exit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.huschle.roster.entity.Student;
import com.huschle.roster.entity.Teacher;
import com.huschle.roster.entity.Enrollment;
import com.huschle.roster.service.IEnrollmentService;
import com.huschle.roster.service.IStudentService;
import com.huschle.roster.service.ITeacherService;

@SpringBootApplication
public class Roster implements CommandLineRunner {

    private static long numOfRecordProcessed = 0;
    private static boolean validInput = false;
    
    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private ITeacherService teacherService;
    
    @Autowired
    private IEnrollmentService enrollmentService;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Roster.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        readCsv(args[0], args[1]);
        printResult();
        exit(0);
    }

    private void readCsv(String srcCsv, String cleanImport) {
        boolean booleanCleanImport = stringToBoolean(cleanImport);
        try {
            URL src = new URL(srcCsv);
            HttpURLConnection urlConnection = (HttpURLConnection) src.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            if(urlConnection.getResponseCode() == 302) {
                src = new URL(urlConnection.getHeaderField("Location"));
                urlConnection = (HttpURLConnection) src.openConnection();
            }

            Reader reader = new InputStreamReader(urlConnection.getInputStream());
            CSVParser parser = null;
            
            try {
                parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                parser.forEach(record -> {
                    List<Optional<Object>> objToLoad = null;
                    try {
                        objToLoad = getObjToLoad(
                        record.get("StudentID"),
                        record.get("State"),
                        record.get("StudentFirstName"),
                        record.get("StudentLastName"),
                        record.get("StudentGender"),
                        record.get("StudentBirthDate"),
                        record.get("StudentRace"),
                        record.get("MealStatus"),
                        record.get("EnglishProficiency"),
                        record.get("NativeLanguage"),
                        record.get("ServiceCode"),
                        record.get("PrimaryDisabilityType"),
                        record.get("IEPReading"),
                        record.get("IEPMath"),
                        record.get("IEPBehavior"),
                        record.get("GiftedAndTalented"),
                        record.get("Section504"),
                        record.get("Mobility"),
                        record.get("TeacherID"),
                        record.get("TeacherFirstName"),
                        record.get("TeacherLastName"),
                        record.get("TeacherEmail"),
                        record.get("School"),
                        record.get("Grade"),
                        record.get("Course"),
                        record.get("Section"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(objToLoad != null) {
                        validInput = true;
                        if(booleanCleanImport == true)
                            load(objToLoad.get(0), objToLoad.get(1), objToLoad.get(2), "A");
                        else
                            load(objToLoad.get(0), objToLoad.get(1), objToLoad.get(2), record.get("Action"));
                    }
                });
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                parser.close();
                reader.close();
            }
              
        }
        catch(Exception e) {
        }   
    }

    private void printResult() {
        //List<Object[]> list = enrollmentService.getEnrollmentInfo();
        String teacherFirstName;
        String teacherLastName;
        if(!validInput) {
            System.out.println("This file includes students with invalid student ID or invalid name.");
            System.out.println("Those rows are not loaded to the database.");
        }
        System.out.println("Number of record processed: " + numOfRecordProcessed);
        /*for(int i=0; i<list.size(); i++){
            Object[] row = (Object[])list.get(i);
            System.out.println(
                ((Teacher)row[0]).getFirstName() + " "
                + ((Teacher)row[0]).getLastName()
                + ": " + row[1]);
        }*/
    }
    
    private List<Optional<Object>> getObjToLoad(
        String studentId,
        String state,
        String studentFirstName,
        String studentLastName,
        String studentGender,
        String studentBirthDate,
        String studentRace,
        String mealStatus,
        String englishProficiency,
        String nativeLanguage,
        String serviceCode,
        String primaryDisabilityType,
        String iepReading,
        String iepMath,
        String iepBehavior,
        String giftedAndTalented,
        String section504,
        String mobility,
        String teacherId,
        String teacherFirstName,
        String teacherLastName,
        String teacherEmail,
        String school,
        String grade,
        String course,
        String section) throws ParseException {

        List<Optional<Object>> objList = new ArrayList<Optional<Object>>();
        boolean validStudentId;
        boolean validName;
        Student student = new Student();
        Teacher teacher = new Teacher();
        
        validStudentId = checkStudentId(studentId);
        validName = checkName(studentFirstName, studentLastName);
        
        student.setId(Integer.parseInt(studentId));
        student.setStateId(state);
        student.setFirstName(studentFirstName);
        student.setLastName(studentLastName);
        student.setGender(studentGender);
        student.setBirthdate(stringToDate(studentBirthDate));
        student.setRace(studentRace);
        student.setMealStatus(mealStatus);
        student.setEnglishProficiency(englishProficiency);
        student.setNativeLanguage(nativeLanguage);
        student.setServiceCode(serviceCode);
        student.setPrimaryDisabilityType(primaryDisabilityType);
        student.setIepReading(stringToBoolean(iepReading));
        student.setIepMath(stringToBoolean(iepMath));
        student.setIepBehavior(stringToBoolean(iepBehavior));
        student.setGiftedTalented(stringToBoolean(giftedAndTalented));
        student.setSection504(stringToBoolean(section504));
        student.setMobility(stringToBoolean(mobility));
        
        teacher.setId(Integer.parseInt(teacherId));
        teacher.setFirstName(teacherFirstName);
        teacher.setLastName(teacherLastName);
        teacher.setEmail(teacherEmail);
        teacher.setSchool(school);
        
        Enrollment enrollment = new Enrollment(student, teacher);
        enrollment.setGrade(grade);
        enrollment.setCourse(course);
        enrollment.setSection(section);
        
        if(!(validStudentId && validName)) {
            student = null;
            teacher = null;
            enrollment = null;
        }
        objList.add(0, Optional.ofNullable(student));
        objList.add(1, Optional.ofNullable(teacher));
        objList.add(2, Optional.ofNullable(enrollment));      
        return objList;       
    }
    
    private boolean checkStudentId(String studentId) {
        boolean valid = true;
        if(studentId.length() > 24) {
            valid = false;
        }
        return valid;
    }
    
    private boolean checkName(String firstName, String lastName) {
        boolean valid = true;
        CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
        valid = asciiEncoder.canEncode(firstName) && asciiEncoder.canEncode(lastName);
        return valid;
    }
    
    private boolean stringToBoolean(String yesOrNo) {
        boolean booleanYesNo = true;
        switch(yesOrNo.toUpperCase()) {
            case "Y": 
                booleanYesNo = true;
                break;
            case "N":
                booleanYesNo = false;
                break;
            default:
                booleanYesNo = true;
                break;
        }
        return booleanYesNo;
    }
    
    private Date stringToDate(String stringDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        return df.parse(stringDate);
    }
    
    private void load(Optional<Object> student, Optional<Object> teacher, Optional<Object> enrollment, String action) {
        numOfRecordProcessed++;
        if(action.equalsIgnoreCase("A")) {
            studentService.addStudent((Student)student.get());
            teacherService.addTeacher((Teacher)teacher.get());
            enrollmentService.addEnrollment((Enrollment)enrollment.get());
        }       
        if(action.equalsIgnoreCase("D")) {
            studentService.deleteStudent((Student)student.get());
            enrollmentService.deleteEnrollment((Enrollment)enrollment.get());
        }    
    }

}
