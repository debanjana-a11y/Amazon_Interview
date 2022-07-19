package Practices;

import javax.swing.*;
import java.sql.Array;
import java.util.*;

public class Amazon_transaction_logs {
    static HashMap<String, Integer> hm = new HashMap<>();

    // Return the user id with crossing threshold
    // ["1 2 10", "1 3 20", "2 2 40", "1 7 40"] threshold  = 2
    // result = List<String> = ["1", "2"]
    public static void main(String[] args) {
        String[] logs = {"1 2 10", "1 3 20", "2 2 40", "1 7 40"};
        int threshold = 2;

        List<String> res = findThreshold(logs, threshold);
        System.out.println(res);
    }

    static List<String> findThreshold(String[] logs, int threshold) {
        for (String str : logs) {
            String[] s = str.split(" ");
            String sender_id = s[0];
            String receiver_id = s[1];
            populateMap(sender_id);
            if (sender_id.equals(receiver_id) == false) {
                populateMap(receiver_id);
            }
        }
        // get user_ids
        return getUserIds(threshold);
    }

    static void populateMap(String user_id) {
        int count = 0;
        if (hm.containsKey(user_id)) {
            count = hm.get(user_id);
        }
        count++;
        hm.put(user_id, count);
    }

    static List<String> getUserIds(int threshold) {
        List<String> list = new ArrayList<>();
        for(Map.Entry<String, Integer> e : hm.entrySet()) {
            int count = e.getValue();
            if (count >= threshold) {
                list.add(e.getKey());
            }
        }
        Collections.sort(list);
        return list;
    }
}
