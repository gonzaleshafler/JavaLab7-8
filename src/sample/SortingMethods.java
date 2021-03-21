package sample;
import java.util.*;

public class SortingMethods {
    //список пацієнтів, які мають вказаний діагноз в порядку зростання номерів
    //медичної картки
    public LinkedList<Patient> sortByDiagnosis(String diagnosis, List<Patient> list) {
        LinkedList<Patient> sortedList = new LinkedList<>(list);
        sortedList.sort(Patient.byMedCardValComparator);
        sortedList.removeIf(p->!p.getDiagnosis().equals(diagnosis));
        return sortedList;
    }
    //список пацієнтів, номер медичної карти у яких знаходиться в заданому
    //інтервалі
    public LinkedList<Patient> sortByMedCard(int begin, int end, List<Patient> list) {
        LinkedList<Patient> sortedList = new LinkedList<>(list);
        sortedList.removeIf(patient -> Integer.parseInt(patient.getMedCard())<begin
                ||Integer.parseInt(patient.getMedCard())>end);
        return sortedList;
    }
    //кількість та список пацієнтів, номер телефону яких починається з вказаної
    //цифри
    public LinkedList<Patient> sortByPhone(String number,List<Patient> list) {
        LinkedList<Patient> sortedList = new LinkedList<>(list);
        sortedList.removeIf(patient -> !patient.checkPhone(number));
        return sortedList;
    }

    //список діагнозів пацієнтів, зареєстрованих у системі без повторів;
    public HashSet<String> getNonRepetDiags(List<Patient> list)
    {
        HashSet<String> set = new HashSet<>();
        Map<String,Integer> map = new HashMap(getStatistic(list));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()==1)
            {
                set.add(entry.getKey());
            }
        }
        return set;
    }
    //для кожного діагнозу визначити кількість пацієнтів, яким він поставлений.
    public Map<String,Integer> getStatistic(List<Patient> list)
    {
        Map<String,Integer> map = new HashMap<>();
        for (Patient p:list) {
            Integer v = map.get(p.getDiagnosis());
            if (v==null)
            {
                v = 0;
            }
            map.put(p.getDiagnosis(),v+1);
        }
        return  map;
    }
    //список діагнозів пацієнтів (без повторів) із зазначенням кількості пацієнтів,
    //що мають цей діагноз у порядку спадання цієї кількості;
    public Map<String,Integer> getSortedStatistic(List<Patient> list)
    {
        Map<String,Integer> map = new LinkedHashMap<>(getStatistic(list));
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<>(map.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedList);
        map.clear();
        for (Map.Entry<String, Integer> entry : sortedList) {
            map.put(entry.getKey(),entry.getValue());
        }
        return  map;
    }
    //Список пациентов с уникальными диагнозами
    public List<Patient> getNotRepetitiveDiag(List<Patient> list)
    {
        HashMap<String, List<Patient>> hashMap = new HashMap<>();

        for (Patient patient : list) {
            if (!hashMap.containsKey(patient.getDiagnosis())) {
                List<Patient> temp = new ArrayList<>();
                temp.add(patient);
                hashMap.put(patient.getDiagnosis(), temp);
            } else {
                hashMap.get(patient.getDiagnosis()).add(patient);
            }
        }
        List<Patient> result = new ArrayList<>();
        for (Map.Entry<String, List<Patient>> stringListEntry : hashMap.entrySet())
        {
            if (hashMap.get(stringListEntry.getKey()).size()==1)
            {
                result.addAll(stringListEntry.getValue());
            }
        }
        return result;
    }

}
