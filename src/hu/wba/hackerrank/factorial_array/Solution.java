package hu.wba.hackerrank.factorial_array;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.hackerrank.com/contests/world-codesprint-12/challenges/factorial-array
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] A = new int[n];
        for (int A_i = 0; A_i < n; A_i++) {
            A[A_i] = in.nextInt();
        }

        StringBuilder sb = new StringBuilder();

        List<Set<Integer>> nonZeroIndices = Stream.generate((Supplier<Set<Integer>>) LinkedHashSet::new)
                .limit(factorMod.size()).collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            if (A[i] < nonZeroIndices.size()) {
                nonZeroIndices.get(A[i]).add(i);
            }
        }

        for (int a0 = 0; a0 < m; a0++) {
            // Write Your Code Here
            int op = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();

            if (op == 2) {
                long sum = 0;
                for (int i = nonZeroIndices.size() - 1; i >= 0; i--) {
                    sum += getFactorMod(i) *
                            nonZeroIndices.get(i).stream().filter(index -> l <= index && index < r).count();
                }
                sb.append(sum % 1000000000).append('\n');
            } else if (op == 1) {
                nonZeroIndices.get(nonZeroIndices.size() - 1).removeIf(index -> l <= index && index < r);

                for (int i = nonZeroIndices.size() - 1; i > 0; i--) {
                    Iterator<Integer> it = nonZeroIndices.get(i - 1).iterator();
                    while (it.hasNext()) {
                        int index = it.next();
                        if (l <= index && index < r) {
                            nonZeroIndices.get(i).add(index);
                            it.remove();
                        }
                    }
                }
            } else {
                for (Set<Integer> indices : nonZeroIndices) {
                    if (indices.remove(l)) {
                        break;
                    }
                }
                if (r < nonZeroIndices.size()) {
                    nonZeroIndices.get(r).add(l);
                }
            }
        }

        System.out.println(sb);

        in.close();
    }

    private static List<Integer> factorMod = new ArrayList<>();

    static {
        long f = 1;
        int i = 0;
        while (f > 0) {
            factorMod.add((int) f);
            f *= ++i;
            f %= 1000000000;
        }
    }

    private static int getFactorMod(int n) {
        return n < factorMod.size() ? factorMod.get(n) : 0;
    }
}
