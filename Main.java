import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		int N = 5;
//		int[][] road = { { 1, 2, 1 }, { 2, 3, 3 }, { 5, 2, 2 }, { 1, 4, 2 }, { 5, 3, 1 }, { 5, 4, 2 } };
//		int K = 3;
		// return 4

		int N = 6;
		int[][] road = { { 1, 2, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 3, 4, 3 }, { 3, 5, 2 }, { 3, 5, 3 }, { 5, 6, 1 } };
		int K = 4;
		// return 4

		System.out.println(new Solution().solution(N, road, K));
	}

}

class Solution {
	class Node {
		int id;
		int len;

		public Node(int id, int len) {
			this.id = id;
			this.len = len;
		}

		public String toString() {
			return "ID : " + id + " = " + len;
		}
	}

	boolean[] visit;
	final int MAX = 5000001;
	public int solution(int N, int[][] road, int K) {
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], MAX);

		for (int i = 0; i < road.length; i++) {
			map[road[i][0] - 1][road[i][1] - 1] = Math.min(map[road[i][0] - 1][road[i][1] - 1], road[i][2]);
			map[road[i][1] - 1][road[i][0] - 1] = Math.min(map[road[i][1] - 1][road[i][0] - 1], road[i][2]);
		}

		visit = new boolean[N];
		visit[0] = true; // 1번 음식점에서 배달
		int[] distance = map[0];
		
		for (int nIdx = 0; nIdx < N - 1; nIdx++) {
			int min = MAX;
			int minIdx = -1;
			
			// find min and minIdx;
			for (int i = 0; i < N; i++) {
				if (!visit[i] && distance[i] != MAX) {
					if (distance[i] < min) {
						min = distance[i];
						minIdx = i;
					}
				}
			}
			
			visit[minIdx] = true;
			for (int i = 0; i < N; i++) {
				if (!visit[i]) {
					if (distance[i] > distance[minIdx] + map[minIdx][i]) {
						distance[i] = distance[minIdx] + map[minIdx][i];
					}
				}
			}
		}
		return (int) (Arrays.stream(distance).filter(i -> i <= K).count() + 1);
	}

}