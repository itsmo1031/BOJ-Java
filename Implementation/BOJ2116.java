package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 2116
 * 주사위 쌓기
 * 골드 5
 *
 * 구현
 * 부르트포스 알고리즘
 */
public class BOJ2116 {
    // 서로 보고있는 면: A-F B-D C-E
    static int N;
    static Dice[] Dices;

    static class Dice {
        int[] dice;

        public Dice(int a, int b, int c, int d, int e, int f) {
            dice = new int[]{a, b, c, d, e, f};
        }

        // 주사위의 한쪽 면이 주어졌을 때 반대쪽 면을 리턴하는 메소드
        public int getOppositeSide(int num) {
            if (num == dice[0]) return dice[5];
            else if (num == dice[1]) return dice[3];
            else if (num == dice[2]) return dice[4];
            else if (num == dice[3]) return dice[1];
            else if (num == dice[4]) return dice[2];
            else return dice[0];
        }

        // 위나 아랫면이 주어졌을 때 나머지 중 최대값을 리턴하는 메소드
        public int getMax(int num) {
            int res = 0;
            for (int i = 1; i < 7; i++) {
                if (dice[i - 1] == num || dice[i - 1] == getOppositeSide(num)) continue;
                res = Integer.max(dice[i - 1], res);
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Dices = new Dice[N];

        // 주어진 N개 주사위의 정보를 담음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            Dices[i] = new Dice(A, B, C, D, E, F);
        }

        int answer = 0;

        for (int i = 1; i < 7; i++) {
            answer = Integer.max(answer, doGame(i));
        }

        System.out.println(answer);
    }

    // 주사위 값 1~6중 하나를 위/아랫면으로 두었을 때 나머지 주사위들의 값들 계산
    // startNum: 첫 주사위의 윗면이 될 숫자
    static int doGame(int startNum) {
        int result = Dices[0].getMax(startNum); // 첫 주사위의 옆면 최대값
        int nextBottom = Dices[0].getOppositeSide(startNum); // 첫 주사위의 반댓면

        for (int i = 1; i < N; i++) {
            // 다음 주사위의 옆면 최대값
            result += Dices[i].getMax(nextBottom);
            // 그 다음 주사위의 밑면이 될 값 저장
            nextBottom = Dices[i].getOppositeSide(nextBottom);
        }

        return result;
    }
}
