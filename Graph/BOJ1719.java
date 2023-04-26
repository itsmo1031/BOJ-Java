package Graph;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1719
 * 택배
 * 골드 3
 *
 * 그래프 이론
 * Dijkstra (다익스트라)
 * Floyd-Warshall (플로이드-워셜)
 */
public class BOJ1719 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 이동 비용을 담을 배열 선언
        int[][] map = new int[n + 1][n + 1];
        // 경로표를 담을 배열 선언
        int[][] result = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            // Integer.MAX_VALUE 사용 시 둘을 더했을 때 오버플로우가 일어남
            // 따라서 Integer의 최대값이 아닌 적절한 최대값(1e9)를 사용함
            Arrays.fill(map[i], (int) 1e9);
            map[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[a][b] = cost;
            map[b][a] = cost;
            // a-b 경로가 최소일 경우를 대비하여 경로표에 입력
            result[a][b] = b;
            result[b][a] = a;
        }

        // 플로이드-워셜 수행
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        // !!!이 문제의 핵심 열쇠!!!
                        // i-j가 최소일 때, 두 군데 이상을 들르는 경우
                        // ex) 1-6은 1-2-5-6이 최소비용!
                        // result[i][j]를 k 값으로 정하면 result[1][6]이 5가 되어버림
                        // result[i][k]는 i-j 이동 경로 중 가장 처음 경로와 같음!
                        // ex) i=1 j=6 k=5일때 result[1][5]=2가 나옴
                        result[i][j] = result[i][k];
                    }
                }
            }
        }

        print(result);
    }

    // 출력을 위한 메서드 정의
    static void print(int[][] graph) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    bw.write("- ");
                } else {
                    bw.write(graph[i][j] + " ");
                }
            }
            bw.newLine();
        }
        bw.close();
    }
}
