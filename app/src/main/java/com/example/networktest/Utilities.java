package com.example.networktest;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.Iterator;

public class Utilities {

    public static ArrayList<int[]> getDigitPairs(String number) {
        ArrayList<int[]> indexPairs = new ArrayList<>();
        for (int i = 0; i < number.length() - 1; i++) {
            for (int j = i + 1; j < number.length(); j++) {
                if (gcd(Character.getNumericValue(number.charAt(i)), Character.getNumericValue(number.charAt(j))) > 1) {
                    indexPairs.add(new int[]{i, j});
                }
            }
        }
        return indexPairs;
    }

    public static int gcd(int a, int b) {
        if (a == 0 && b == 0) {
            return -1;
        }
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return b;
    }

    public static SpannableString markPairs(ArrayList<int[]> indexPairs, String number) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        Iterator<int[]> itr = indexPairs.iterator();
        while (itr.hasNext()) {
            int[] pair = itr.next();
            int firstDigitIndex = pair[0];
            int secondDigitIndex = pair[1];
            SpannableString boldText = new SpannableString(number + " → (" + firstDigitIndex + ", " + secondDigitIndex + ")");
            StyleSpan firstDigitSpan = new StyleSpan(Typeface.BOLD);
            StyleSpan secondDigitSpan = new StyleSpan(Typeface.BOLD);
            boldText.setSpan(firstDigitSpan, firstDigitIndex, firstDigitIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            boldText.setSpan(secondDigitSpan, secondDigitIndex, secondDigitIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.append(boldText);
            if (itr.hasNext()) {
                sb.append("\n");
            }
        }
        return sb.length() != 0 ? SpannableString.valueOf(sb) : SpannableString.valueOf("No common Devisors!");
    }

    public static SpannableString markPairs(String number) {
        return markPairs(getDigitPairs(number), number);
    }

}
