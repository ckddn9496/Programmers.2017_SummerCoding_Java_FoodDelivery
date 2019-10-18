# Programmers.2017_SummerCoding_Java_FoodDelivery

## 프로그래머스 > 코딩테스트 연습 > 2017 서머코딩 > 배달

### 1 문제설명

문제 : https://programmers.co.kr/learn/courses/30/lessons/12978

input으로 마을의 개수 `N`, 마을간의 거리를 담은 이차원 배열 `road[][]` 최대 배달가능 거리 `K`가 들어온다. `1`번 마을에서 시작하여 `N`개의 마을중 `K`안에 도착할 수 있는 마을의 개수를 return하는 문제.

### 2. 풀이

시작점이 고정된 다익스트라 알고리즘을 이용하는 문제. 주의할점으로는 같은 마을사이의 길이 여러개 나올 수 있다. input값에 대해 최소길이의 값으로 거리를 지정해주어야 한다.

```java

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

```

모든 거리를 찾은후 `K`이하인 마을의 개수에 첫번째 마을까지 합쳐서 개수를 return한다.
```java
return (int) (Arrays.stream(distance).filter(i -> i <= K).count() + 1);
```
