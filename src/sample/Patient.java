package sample;

import java.util.Comparator;

public class Patient {
    int id;
    private String address;
    private String phone;
    private String medCard;
    private String diagnosis;
    private String firstName;   // Имя
    private String middleName;  // Отчество
    private String lastName;    // Фамилия

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return String.join(" ", lastName, firstName, middleName);
    }

    public int setName(String name) {
        String[] buff = name.split(" ");
        if (buff.length == 3) {
            lastName = buff[0];
            firstName = buff[1];
            middleName = buff[2];
            return 0;
        }
        return -1;
    }
    Patient(int id, String firstName, String middleName, String lastName, String address) {
        String[] diagnoses = {"Отравление", "Коронавирус", "Шиза", "Астения"};
        setId(id);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setAddress(address);
        setPhone("" + (int)(0));
        setMedCard(String.valueOf(0));
        setDiagnosis(diagnoses[(int)(Math.random() * 4)]);
    }

    Patient(int id, String firstName, String middleName, String lastName, String address, String phone, String medCard, String diagnosis) {
        this.id = id;
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        this.address = address;
        this.phone = phone;
        this.medCard = medCard;
        this.diagnosis = diagnosis;
    }

    Patient(String s) {
        String[] data = s.split(", ");
        setId(Integer.parseInt(data[0]));
        setName(data[1]);
        setMedCard(data[2]);
        setDiagnosis(data[3]);
        setAddress(data[4]);
        setPhone(data[5]);
    }

    Patient(int id) {
        this.id = id;
        setFirstName("");
        setMiddleName("");
        setLastName("");
        setMedCard("0");
        setDiagnosis("Неизвестно");
        setAddress("Отсутствует");
        setPhone("000000000");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedCard() {
        return medCard;
    }

    public void setMedCard(String medCard) {
        this.medCard = medCard;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean checkDiagnosis(String diagnosis) {
        return diagnosis.equals(getDiagnosis());
    }

    public boolean checkMedCard(int begin, int end) {
        int medCard = Integer.parseInt(getMedCard());
        return (medCard >= begin && medCard <= end);
    }

    public boolean checkPhone(String number) {

        String phone=getPhone();
        if (phone.indexOf(number)==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public String toString() {
        return String.join(", ", String.valueOf(getId()), getName(), String.valueOf(getMedCard()), getDiagnosis(), getAddress(), getPhone());
    }

    public static Comparator<Patient> byMedCardValComparator=new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            if (Integer.parseInt(o1.getMedCard())  - Integer.parseInt(o2.getMedCard())!=0)
                return Integer.parseInt(o1.getMedCard())  - Integer.parseInt(o2.getMedCard());
            else
                return o1.getFirstName().compareTo(o2.getFirstName());
        }
    };

}


