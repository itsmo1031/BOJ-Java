package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 16948
 * 데스 나이트
 * 실버 1
 *
 * 그래프 이론
 * 그래프 탐색
 * BFS (너비 우선 탐색)
 */
public class BOJ16948 {

    static final int[][] D = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
    static int N;
    static boolean[][] visited;
    static Pos goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        Pos start = new Pos(r1, c1, 0);

        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        goal = new Pos(r2, c2);

        System.out.println(bfs(start));
    }

    static int bfs(Pos pos) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int[] d : D) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];
                int nc = cur.cnt + 1;

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                if (nx == goal.x && ny == goal.y) return nc;

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny, nc));
            }
        }

        return -1;
    }

    static class Pos {
        int x;
        int y;
        int cnt = 0;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
