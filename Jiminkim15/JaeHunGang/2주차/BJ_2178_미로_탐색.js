const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((v) => parseInt(v, 10));
const miro = input
  .slice(1)
  .map((line) => line.split("").map((v) => parseInt(v, 10)));

// 이동 벡터
const dy = [-1, 1, 0, 0];
const dx = [0, 0, -1, 1];

// 방문 처리 2차원 배열
const visited = Array.from({ length: N }, () => Array(M).fill(false));

// BFS 큐 초기화
// y, x, moveCount
const queue = [[0, 0, 1]];
visited[0][0] = true;

while (queue.length) {
  const [y, x, moveCount] = queue.shift();

  // 목표 지점에 도달하면 출력하고 종료
  if (y === N - 1 && x === M - 1) {
    console.log(moveCount);
    return;
  }

  // 상하좌우 이동
  for (let i = 0; i < 4; i++) {
    const ny = y + dy[i];
    const nx = x + dx[i];

    if (
      ny >= 0 &&
      ny < N &&
      nx >= 0 &&
      nx < M &&
      miro[ny][nx] &&
      !visited[ny][nx]
    ) {
      visited[ny][nx] = true;
      queue.push([ny, nx, moveCount + 1]);
    }
  }
}
