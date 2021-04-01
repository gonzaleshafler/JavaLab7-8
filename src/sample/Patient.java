package sample;

import java.util.Comparator;

public class Patient {
    int id;
    private String address;
    private String phone;
    private String medCard;
    private String diagnosis;
    private String firstName;
    private String middleName;
    private String lastName;

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

    Patient(int id, String firstName, String middleName, String lastName, String address, String phone, String medCard, String diagnosis) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.medCard = medCard;
        this.diagnosis = diagnosis;
    }
    Patient()
    {

    }

    public int getId() {
        return id;
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
        return String.join(", ", String.valueOf(id), getName(), String.valueOf(medCard), diagnosis, address, phone);
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


