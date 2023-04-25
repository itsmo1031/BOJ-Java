package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 20046
 * Road Reconstruction
 * 골드 4
 *
 * 그래프 이론
 * 그래프 탐색
 * Dijkstra (다익스트라)
 */
public class BOJ20046 {

    static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int m;
    static int n;
    static int[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        if (map[0][0] == -1) {
            return -1;
        }

        PriorityQueue<Pos> q = new PriorityQueue<>();
        q.offer(new Pos(0, 0, map[0][0]));
        dist[0][0] = map[0][0];

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int[] d : dir) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n || map[nx][ny] == -1) continue;

                int nc = cur.cost + map[nx][ny];
                if (nc < dist[nx][ny]) {
                    dist[nx][ny] = nc;
                    q.offer(new Pos(nx, ny, nc));
                }
            }
        }

        return dist[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist[m - 1][n - 1];
    }

    static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }
}
