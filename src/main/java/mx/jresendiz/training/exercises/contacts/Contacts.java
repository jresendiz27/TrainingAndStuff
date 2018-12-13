package mx.jresendiz.training.exercises.contacts;

import java.util.*;

public class Contacts {
    public static void main(String[] args) {
        ArrayList<String> contactInformation = new ArrayList<>();
        HashMap<String, Integer> namesDictionary = new HashMap<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int a0 = 0; a0 < n; a0++) {
            String op = in.next();
            String data = in.next();
            String acc = "";
            // code here
            if ("add".equals(op)) {
                contactInformation.add(data);
                for (int i = 0; i < data.length(); i++) {
                    acc += data.charAt(i);
                    if (namesDictionary.containsKey(acc)) {
                        Integer numberOfElements = namesDictionary.get(acc);
                        numberOfElements++;
                        namesDictionary.put(acc, numberOfElements);
                    } else {
                        namesDictionary.put(acc, 1);
                    }
                }
            }

            if ("find".equals(op)) {
                Integer count = namesDictionary.get(data);
                if (count != null) {
                    System.out.println(count.toString());
                } else {
                    System.out.println(new Integer(0));
                }
            }
        }
    }
}