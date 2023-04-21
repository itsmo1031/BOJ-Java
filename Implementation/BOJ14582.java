package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 14582
 * 오늘도 졌다
 * 실버 5
 *
 * 구현
 */
public class BOJ14582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] gemini = new int[9];
        int[] gulliver = new int[9];
        int gap = 0;
        String answer = "No";

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int next = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    gemini[j] = next;
                } else {
                    gulliver[j] = next;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            gap += gemini[i];
            if (gap > 0) {
                answer = "Yes";
            }
            gap -= gulliver[i];
        }

        System.out.println(answer);
    }
}
