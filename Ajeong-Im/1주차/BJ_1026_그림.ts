import * as fs from "fs";

// 파일 경로 설정: 리눅스 환경에서는 '/dev/stdin', 그 외 환경에서는 './input.txt' 사용
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

const input = fs.readFileSync(filePath, "utf8");
const lines = input
  .split("\n")
  .map((line) => line.trim())
  .filter((line) => line !== "");

const [n, m] = lines[0].split(" ").map(Number);

// 도화지 정보 저장
const grid: number[][] = [];
for (let i = 1; i <= n; i++) {
  const row = lines[i].split(" ").map(Number);
  grid.push(row);
}

// 방문 여부 기록할 2차원 배열
const visited: boolean[][] = Array.from({ length: n }, () =>
  Array(m).fill(false)
);

let pictureCount = 0;
let maxArea = 0;

// 그림의 넓이 계산하는 bfs 함수
function bfs(startRow: number, startCol: number): number {
  const queue: [number, number][] = [];
  queue.push([startRow, startCol]);
  visited[startRow][startCol] = true;

  let area = 0; // 현재 그림의 넓이 저장

  // 상하좌우 이동을 위한 방향 배열
  const directions = [
    [-1, 0], // 위
    [1, 0], // 아래
    [0, -1], // 왼쪽
    [0, 1], // 오른쪽
  ];

  while (queue.length > 0) {
    const [row, col] = queue.shift()!;
    area++;

    // 상하좌우 셀 확인
    for (const [dr, dc] of directions) {
      const newRow = row + dr;
      const newCol = col + dc;

      if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
        if (!visited[newRow][newCol] && grid[newRow][newCol] === 1) {
          visited[newRow][newCol] = true;
          queue.push([newRow, newCol]);
        }
      }
    }
  }
  return area;
}

// 모든 도화지 확인
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (!visited[i][j] && grid[i][j] === 1) {
      pictureCount++;
      const currentArea = bfs(i, j);
      maxArea = Math.max(maxArea, currentArea);
    }
  }
}

console.log(pictureCount);
console.log(maxArea);
