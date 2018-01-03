package mx.jresendiz.training.hackerrank.MakingAnagrams;

import java.util.Arrays;
import java.util.Scanner;

public class MakingAnagrams {

    public static int numberNeeded(String first, String second) {
        int[] letterCounts = new int[26];

        for (Character character : first.toCharArray()) {
            letterCounts[character - 'a']++;
        }

        for (Character character : second.toCharArray()) {
            letterCounts[character - 'a']--;
        }

        return Arrays.stream(letterCounts).reduce(0, (x, y) -> Math.abs(x) + Math.abs(y));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
