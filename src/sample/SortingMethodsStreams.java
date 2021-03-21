package sample;

import java.util.*;
import java.util.stream.Collectors;

public class SortingMethodsStreams {
    //список пацієнтів, які мають вказаний діагноз в порядку зростання номерів
    //медичної картки
    public ArrayList<Patient> sortByDiagnosis(String diagnosis, List<Patient> list) {

        return (ArrayList<Patient>) list.stream()
                .filter(p->p.getDiagnosis().equalsIgnoreCase(diagnosis))
                .sorted(Patient.byMedCardValComparator)
                .collect(Collectors.toList());

    }



    //список пацієнтів, номер медичної карти у яких знаходиться в заданому
    //інтервалі
    public ArrayList<Patient> sortByMedCard(int begin, int end, List<Patient> list) {

        return (ArrayList<Patient>) list.stream()
                .filter(patient -> !(Integer.parseInt(patient.getMedCard())<begin
                ||Integer.parseInt(patient.getMedCard())>end))
                .collect(Collectors.toList());

    }



    //кількість та список пацієнтів, номер телефону яких починається з вказаної
    //цифри
    public ArrayList<Patient> sortByPhone(String number,List<Patient> list) {

        return   (ArrayList<Patient>) list.stream().filter(patient -> patient.checkPhone(number)).collect(Collectors.toList());
    }



    //список діагнозів пацієнтів (без повторів) із зазначенням кількості пацієнтів,
    //що мають цей діагноз у порядку спадання цієї кількості;
    public LinkedHashMap<String,Integer> getSortedStatistic(List<Patient> list)
    {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        getStatistic(list).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue().intValue()));
        return  result;
    }


    //для кожного діагнозу визначити кількість пацієнтів, яким він поставлений.
    public  HashMap<String,Long> getStatistic(List<Patient> list)
    {
        return  (HashMap<String, Long>) list.stream().collect(Collectors.groupingBy(Patient::getDiagnosis,Collectors.counting()));
    }
    //список діагнозів пацієнтів, зареєстрованих у системі без повторів;
    public ArrayList<String> getNonRepetDiags(List<Patient> list)
    {
        Map<String,Long> map =new HashMap<>(getStatistic(list).entrySet().stream()
                                            .filter(p->p.getValue()==1)
                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return new ArrayList<>(map.keySet());
    }


    public ArrayList<Patient> getNotRepetitiveDiag(List<Patient> list)
    {
        return (ArrayList<Patient>) list.stream().filter(p -> getNonRepetDiags(list).contains(p.getDiagnosis())).collect(Collectors.toList());
    }



}
