const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const K = parseInt(input[0], 10); // 말처럼 이동할 수 있는 횟수
const [W, H] = input[1].split(" ").map((v) => parseInt(v, 10)); // 크기
const grid = input
  .slice(2)
  .map((line) => line.split(" ").map((v) => parseInt(v, 10)));

// 원숭이 이동 상하좌우
const monkeyMoves = [
  [0, 1],
  [0, -1],
  [-1, 0],
  [1, 0],
];

// 말 이동 상하좌우
const horseMoves = [
  [-1, 2],
  [1, 2],
  [-1, -2],
  [1, -2],
  [-2, 1],
  [-2, -1],
  [2, 1],
  [2, -1],
];

// 3차원 배열 -> 같은 장소 다른 방법으로 방문
const visited = Array.from({ length: H }, () =>
  Array.from({ length: W }, () => Array(K + 1).fill(false))
);

// BFS(Breath-First Search) 큐
const queue = [[0, 0, 0, 0]];
visited[0][0][0] = true;

while (queue.length) {
  const [y, x, moveCount, horseCount] = queue.shift();

  // 목표 지점 도달 시
  if (y === H - 1 && x === W - 1) {
    console.log(moveCount);
    return;
  }
  // 말 이동
  if (horseCount < K) {
    for (const [dy, dx] of horseMoves) {
      const ny = y + dy;
      const nx = x + dx;
      const nk = horseCount + 1;

      if (
        ny >= 0 &&
        ny < H &&
        nx >= 0 &&
        nx < W &&
        grid[ny][nx] === 0 &&
        !visited[ny][nx][nk]
      ) {
        visited[ny][nx][nk] = true;
        queue.push([ny, nx, moveCount + 1, nk]);
      }
    }
  }

  // 일반 이동
  for (const [dy, dx] of monkeyMoves) {
    const ny = y + dy;
    const nx = x + dx;

    if (
      ny >= 0 &&
      ny < H &&
      nx >= 0 &&
      nx < W &&
      grid[ny][nx] === 0 &&
      !visited[ny][nx][horseCount]
    ) {
      visited[ny][nx][horseCount] = true;
      queue.push([ny, nx, moveCount + 1, horseCount]);
    }
  }
}

console.log(-1);
