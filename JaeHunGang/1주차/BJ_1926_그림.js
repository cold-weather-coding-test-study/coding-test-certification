// 행렬 크기, 입력
const [inputSize, ...input] = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

// 각 요소를 숫자로 변환
const [row, col] = inputSize.split(' ').map(val => +val);

// ' ' 기준으로 각 행의 요소를 숫자로 변환
const board = input.map(row => [...row.split(' ').map(val => +val)]);

// 2차원 배열 false로 초기화
const visited = Array.from({ length: row }, () => Array.from({ length: col }, () => false));

// 2차원 배열 이동 (오, 왼, 아, 위)
const dirs = [[0, 1], [0, -1], [1, 0], [-1, 0],];

// 그림의 크기 저장 
const drawings = [];

const measureDrawingSize = (x, y) => {
  let size = 1; // 시작 좌표 = 1
  const queue = [[x, y]]; // 큐 초기화

  while (queue.length) { // 큐가 빌 때까지 반복
    const [x, y] = queue.shift(); // 현재 좌표 저장

    dirs.forEach(dir => {
      const xPos = dir[0] + x; // col 이동
      const yPos = dir[1] + y; // row 이동

      // 왼쪽 위쪽 범위 && 오른쪽 아래쪽 범위 && 그림이 1이라면 && false가 아닐때(=방문 안했을 때)
      if (xPos >= 0 && yPos >= 0 && xPos < col && yPos < row && board[yPos][xPos] && !visited[yPos][xPos]) {
        visited[yPos][xPos] = true; // 방문한 위치 true로 초기화
        size += 1; // 그림 크기 +=1
        queue.push([xPos, yPos]); // 탐색할 queue 초기화
      }
    });
  }
  return size;
};

for (let y = 0; y < row; y++) {
  for (let x = 0; x < col; x++) {
    if (!board[y][x] || visited[y][x]) continue; // board가 0 or 방문한 좌표는 건너 뜀

    visited[y][x] = true;
    drawings.push(measureDrawingSize(x, y)); // 그림 크기 저장 배열
  }
}

console.log(drawings.length ?? 0); // 그림의 갯수, 없으면 0출력
console.log(drawings.length ? Math.max(...drawings) : 0); // 배열 중 가장 큰 값 출력, 없으면 0 출력