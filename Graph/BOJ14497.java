package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 14497
 * 주난의 난(難)
 * 골드 4
 *
 * 그래프 이론
 * 그래프 탐색
 * Dijkstra (다익스트라)
 * 0-1 BFS (0-1 너비 우선 탐색)
 */
public class BOJ14497 {
    // 현재 좌표와 비용을 담을 수 있는 클래스 선언
    // PriorityQueue를 사용 가능하도록 Comparable 인터페이스 상속
    static class Pos implements Comparable<Pos> {
        final int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // 비용이 작은 값 순으로 정렬됨
        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N, M, x1, y1, x2, y2;
    static char[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // * 10이상의 숫자를 받아야 할 경우 charAt(0)-'0' 말고 parseInt를 써야 함!!!
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        map = new char[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        System.out.println(dijkstra(x1, y1));
    }

    static int dijkstra(int x, int y) {
        PriorityQueue<Pos> q = new PriorityQueue<>();
        q.offer(new Pos(x, y, 0));
        dist[x][y] = 0;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + d[i][0];
                int ny = cur.y + d[i][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                int nc = map[nx][ny] == '0' ? cur.cost : cur.cost + 1;
                if (nc < dist[nx][ny]) {
                    dist[nx][ny] = nc;
                    q.offer(new Pos(nx, ny, nc));
                }
            }
        }
        return dist[x2][y2];
    }
}
