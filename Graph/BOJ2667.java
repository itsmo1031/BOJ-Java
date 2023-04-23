package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 2667
 * 단지번호붙이기
 * 실버 1
 *
 * 그래프 이론
 * 그래프 탐색
 * BFS (너비 우선 탐색)
 * DFS (깊이 우선 탐색)
 */
public class BOJ2667 {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> result = new ArrayList<>();
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        boolean[][] visited = new boolean[N][N];
        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    result.add(bfs(new int[]{i, j}, map, visited));
                }
            }
        }
        result.sort(Comparator.naturalOrder());
        System.out.println(result.size());
        result.forEach(System.out::println);
    }

    static int bfs(int[] pos, int[][] graph, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(pos);
        int count = 1;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < N &&
                        0 <= ny && ny < N &&
                        graph[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    count++;
                }
            }
        }
        return count;
    }
}
