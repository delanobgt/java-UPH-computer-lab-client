package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import model.LabTransaction;
import model.Student;
import properties.PropertiesLoader;
import util.Utility;

public class DbLocalLabTransaction {
    
    private static final String FILE_EXTENSION = "db";
    private static final String LOCAL_DB_URL_LAB = PropertiesLoader.get("LOCAL_DB_URL_LAB");
    private static final String FILENAME = String.format("%s.%s", LOCAL_DB_URL_LAB, FILE_EXTENSION);
    private static ConcurrentHashMap<Integer, LabTransaction> db;
    private static int runningID;
    
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
    private synchronized static ConcurrentHashMap<Integer, LabTransaction> load(String path) {
        ConcurrentHashMap<Integer, LabTransaction> db = new ConcurrentHashMap<>();

        String line = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        File file = null;
        runningID = -1;
        
        try {
            file = new File(FILENAME);
            if (!file.exists()) file.createNewFile();
            
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                String[] cols = line.split(";");
                runningID = Math.max(runningID, Integer.parseInt(cols[0].trim()));
                db.put(Integer.parseInt(cols[0].trim()), new LabTransaction(
                        Integer.parseInt(cols[0].trim()),
                        cols[1].trim(),
                        cols[2].trim(),
                        cols[3].trim(),
                        Integer.parseInt(cols[4].trim()),
                        cols[5].trim(),
                        cols[6].trim().equals("null") ? null : Integer.parseInt(cols[6].trim())
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (Exception ex) {ex.printStackTrace();}
        }
        runningID += 1;
        return db;
    }
    private synchronized static void save(ConcurrentHashMap<Integer, LabTransaction> db, String path) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(new File(path));
            bufferedWriter = new BufferedWriter(fileWriter);
            for (LabTransaction labTransaction : db.values()) {
                bufferedWriter.write(String.format("%d;%s;%s;%s;%d;%s;%s", 
                            labTransaction.getId(), 
                            labTransaction.getStudentID(), 
                            labTransaction.getSignIn(),
                            labTransaction.getSignOut(),
                            labTransaction.getPcNumber(),
                            labTransaction.getLabLocation(),
                            labTransaction.getCloudID() == null ? "null" : labTransaction.getCloudID()));
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
    
    /*
     * return newly generated key
     */
    public static Integer createSignIn(Student student) {
        LabTransaction labTransaction = new LabTransaction(
                student, 
                getCurrentTimeStamp().toString(), 
                getLabPcNumberFromSystem(), 
                getLabLocationFromSystem());
        labTransaction.setId(runningID);
        db.put(runningID, labTransaction);
        return runningID++;
    }
    
    public static void updateSignOut(Integer transactionId) {
        LabTransaction labTransaction = db.get(transactionId);
        labTransaction.setSignOut(getCurrentTimeStamp().toString());
    }
    
    public static void deleteLabTransactionById(int transactionId) {
        db.remove(transactionId);
    }
    
    public static List<LabTransaction> getLabTransactionList() {
        return new ArrayList<>(db.values());
    }
    
    private static Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        return timestamp;
    }
    private static Integer getLabPcNumberFromSystem() {
        return Integer.valueOf(System.getenv("LAB_PC_NUMBER"));
    }
    private static String getLabLocationFromSystem() {
        return System.getenv("LAB_LOCATION");
    }
}
