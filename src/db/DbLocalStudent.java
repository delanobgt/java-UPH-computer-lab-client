package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import model.Student;
import properties.PropertiesLoader;
import util.Utility;

public class DbLocalStudent {

    private static final String FILE_EXTENSION = "db";
    private static final String LOCAL_DB_URL_STUDENT = PropertiesLoader.get("LOCAL_DB_URL_STUDENT");
    private static final String FILENAME = String.format("%s.%s", LOCAL_DB_URL_STUDENT, FILE_EXTENSION);
    private static ConcurrentHashMap<String, Student> db;

    static {
        db = load(FILENAME);
    }

    public synchronized static void load() {
        File file = new File(FILENAME);
        Utility.unhideFile(file);
        db = load(FILENAME);
        Utility.hideFile(file);
    }
    public synchronized static void save() {
        File file = new File(FILENAME);
        Utility.unhideFile(file);
        save(db, FILENAME);
        Utility.hideFile(file);
    }
    private static ConcurrentHashMap<String, Student> load(String path) {
        ConcurrentHashMap<String, Student> db = new ConcurrentHashMap<>();

        String line = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        File file = null;
        
        try {
            file = new File(path);
            if (!file.exists()) file.createNewFile();
            
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                String[] cols = line.split(";");
                if (cols.length < 3) continue;
                db.put(cols[0].trim(), new Student(cols[0].trim(), cols[1].trim(), cols[2].trim()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (Exception ex) {ex.printStackTrace();}
        }
        return db;
    }
    private static void save(ConcurrentHashMap<String, Student> db, String path) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(new File(path));
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : db.values()) {
                bufferedWriter.write(
                    String.format("%s;%s;%s", student.getStudentId(), student.getName(), student.getStudyProgram())
                );
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (fileWriter != null) fileWriter.close();
            } catch (Exception ex) {ex.printStackTrace();}
        }
    }

    public static Student getStudentByNim(String nim) {
        return db.getOrDefault(nim, null);
    }

    public static void addStudent(Student student) {
        db.put(student.getStudentId(), student);
    }

    public static List<Student> getStudentList() {
        return new ArrayList<>(db.values());
    }
}
