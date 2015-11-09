package nitka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RepeatedNumbers {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList( new Integer[]{-2,-2,-2,5,5,5,3,3,3,5,1,2,5,5,5,8,3,6,7,4,4,4,4,4,4,6,7,4,4,6,7,2,2,2,1,1,1}));
        delete3InRow(list);
        System.out.println(Arrays.toString(list.toArray()));

        List<Integer> list2 = new ArrayList<>(Arrays.asList( new Integer[]{1,2,3,4,2,4,6,2,6,6,5,3,7,6}));
        deleteNfrequent(list2,3);
        System.out.println(Arrays.toString(list2.toArray()));
    }

    static void deleteNfrequent(List<Integer> list, int frequencyThreshold){
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (Integer i : list){
            if(frequencies.containsKey(i)){
                frequencies.put(i,frequencies.get(i) + 1);
            } else {
                frequencies.put(i,1);
            }
        }
        for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
            Integer num = i.next();
            if(frequencies.containsKey(num) && frequencies.get(num) >= frequencyThreshold)
                i.remove();
        }
    }

    static void delete3InRow(List<Integer> list){
        if(list.size() < 3)return;

        boolean[] sequencesStart = new boolean[list.size()];
        int[] sequencesCount = new int[list.size()];
        int currentSequenceStart = -1;
        boolean activeSequence = false;

        for(int i = 2; i < list.size();i++){
            if(list.get(i-2).equals(list.get(i-1))  && list.get(i-1).equals(list.get(i))){
                if(activeSequence){
                    sequencesCount[currentSequenceStart]++;
                } else {
                    activeSequence = true;
                    currentSequenceStart = i-2;
                    sequencesStart[i-2] = true;
                    sequencesCount[i-2] = 3;
                }
            } else {
                activeSequence = false;
            }
        }

        int countDeleted = 0;
        for(int i = 0; i < sequencesStart.length;i++){
            if(sequencesStart[i]){
                int countDeletedCurrent = 0;
                while(sequencesCount[i] > 0){
                    list.remove(i-countDeleted);
                    sequencesCount[i]--;
                    countDeletedCurrent++;
                }
                countDeleted += countDeletedCurrent;
            }
        }

    }

}
