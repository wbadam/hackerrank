package hu.wba.hackerrank.breaking_sticks;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/contests/world-codesprint-12/challenges/breaking-sticks
 */
public class Solution {

    static long longestSequence(long[] a) {
        //  Return the length of the longest possible sequence of moves.
        long moves = 0;
        for (long n : a) {
            moves += numOfMoves(n);
        }
        return moves;
    }

    private static long numOfMoves(long n) {
        List<Long> factor = new ArrayList<>();
        long d = 1;
        while ((d = smallestDivisor(n, d)) > 1) {
            factor.add(d);
            n /= d;
        }
        if (n > 1) {
            factor.add(n);
        }

        long moves = 1;
        long s = 1;
        for (int i = factor.size() - 1; i >= 0; i--) {
            moves += factor.get(i) * s;
            s *= factor.get(i);
        }
        return moves;
    }

    private static long smallestDivisor(long n, long d) {
        if (n % 2 == 0) {
            return 2;
        } else {
            d = Math.max(3, d);
            while ((n % d != 0) && (d < n / d)) {
                d+=2;
            }

            if (n % d == 0) {
                return d;
            }

            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
        }
        long result = longestSequence(a);
        System.out.println(result);
        in.close();
    }
}