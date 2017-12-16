package hu.wba.hackerrank.red_knights_shortest_path;

import java.util.*;

/**
 * https://www.hackerrank.com/contests/world-codesprint-12/challenges/red-knights-shortest-path
 */
public class Solution {
    private enum Direction {
        UL(-2, -1), UR(-2, 1), R(0, 2), LR(2, 1), LL(2, -1), L(0, -2);

        private int i;
        private int j;
        Direction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getReverseI() {
            return -getI();
        }

        public int getReverseJ() {
            return -getJ();
        }
    }
    
    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        //  Print the distance along with the sequence of moves.
        if ((i_end - i_start) % 2 != 0
                || (j_end - j_start) % 2 == 0 && (i_end - i_start) % 4 != 0
                || (j_end - j_start) % 2 != 0 && Math.abs((i_end - i_start) % 4) != 2) {
            System.out.println("Impossible");
        } else {
            int count = 0;
            StringBuilder sb = new StringBuilder();

            Direction[][] visited = new Direction[n][n];
            Queue<Integer> queue = new LinkedList<>();
            int i, j;
            int ni, nj;
            int node;

            visited[i_start][j_start] = Direction.UL;   // dummy value
            queue.add(i_start * n + j_start);

            while (!queue.isEmpty()) {
                node = queue.poll();
                i = node / n;
                j = node % n;
                for (Direction d : Direction.values()) {
                    ni = i + d.getI();
                    nj = j + d.getJ();
                    if (ni >= 0 && ni < n && nj >= 0 && nj < n && visited[ni][nj] == null) {
                        if (ni == i_end && nj == j_end) {
                            visited[ni][nj] = d;
                            queue.clear();
                            break;
                        } else {
                            visited[ni][nj] = d;
                            queue.add(ni * n + nj);
                        }
                    }
                }
            }

            List<Direction> path = new ArrayList<>();
            i = i_end;
            j = j_end;
            while (!(i == i_start && j == j_start)) {
                Direction d = visited[i][j];
                path.add(d);
                i += d.getReverseI();
                j += d.getReverseJ();
                count++;
            }

            sb.append(count).append('\n');
            for (i = path.size() - 1; i >= 0; i--) {
                sb.append(path.get(i)).append(' ');
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int i_start = in.nextInt();
        int j_start = in.nextInt();
        int i_end = in.nextInt();
        int j_end = in.nextInt();
        printShortestPath(n, i_start, j_start, i_end, j_end);
        in.close();
    }
}
