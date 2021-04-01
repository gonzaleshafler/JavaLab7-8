package sample;
import javafx.scene.control.Alert;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BasicFunctionality {
    public List<Patient> patients = new ArrayList<>();
    BasicFunctionality(File file) {
        readData(file);
    }

    public void readData(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            patients = new ArrayList<>();
            while ((s = br.readLine()) != null) {
                String[] data = s.split(", ");
                Patient p = new Patient(Integer.parseInt(data[0])," "," "," ",data[4],data[5],data[2],data[3]);
                p.setName(data[1]);
                patients.add(p);
            }
            br.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Что-то пошло не так!");
            alert.showAndWait();
        }
    }

    public int saveInFile(File file) {
        if (file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))){
                ListIterator<Patient> listIterator = patients.listIterator();
                for (;listIterator.hasNext();bw.newLine()) {
                    Patient p = listIterator.next();
                    bw.write(p.toString());
                }
                return 0;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Не удалось открыть файл!");
                alert.showAndWait();
                return -1;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Файл не выбран");
            alert.setContentText("Для сохранения информации сначала необходимо выбрать файл.");
            alert.showAndWait();
            return -1;
        }
    }

    public ArrayList<Patient> getData() {
        return (ArrayList<Patient>) patients;
    }

    public void addPatient() {

        Patient p = new Patient(patients.size()+1,
                "None",
                " ",
                " ",
                "None",
                "None",
                 String.valueOf(patients.size()+1),
                "None");
        patients.add(p);
    }

}
