package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1652
 * 누울 자리를 찾아라
 * 실버 5
 *
 * 구현
 * 문자열
 */
public class BOJ1652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] room = new String[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            System.arraycopy(line, 0, room[i], 0, N);
        }

        System.out.println(findSeat(room) + " " + findSeat(flip(room)));
    }

    public static int findSeat(String[][] arr) {
        int count = 0;

        for (String[] str : arr) {
            String[] target = String.join("", str).split("X");
            for (String t : target) {
                if (t.chars().filter(c -> c == '.').count() >= 2) {
                    count++;
                }
            }
        }

        return count;
    }

    public static String[][] flip(String[][] arr) {
        int n = arr.length;
        String[][] result = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = arr[j][i];
            }
        }

        return result;
    }
}