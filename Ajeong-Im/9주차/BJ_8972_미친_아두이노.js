const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [R, C] = input[0].split(" ").map(Number);
let board = input.slice(1, 1 + R).map((row) => row.split(""));
const moves = input[1 + R].split("").map(Number);

// 방향 델타 (1~9번, 0번은 더미)
const dx = [0, 1, 1, 1, 0, 0, 0, -1, -1, -1];
const dy = [0, -1, 0, 1, -1, 0, 1, -1, 0, 1];

// 종수 & 아두이노 초기 위치 찾기
let jongsu = [0, 0];
let arduino = [];

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (board[i][j] === "I") jongsu = [i, j];
    if (board[i][j] === "R") arduino.push([i, j]);
  }
}

for (let turn = 0; turn < moves.length; turn++) {
  const dir = moves[turn];
  let [x, y] = jongsu;
  const nx = x + dx[dir];
  const ny = y + dy[dir];

  // 종수 이동
  jongsu = [nx, ny];

  // 이동 즉시 충돌 체크
  let dead = false;
  for (const [ax, ay] of arduino) {
    if (ax === nx && ay === ny) {
      console.log(`kraj ${turn + 1}`);
      return;
    }
  }

  // 미친 아두이노 이동 처리
  const tempMap = Array.from({ length: R }, () => Array(C).fill(0));
  const nextArduino = [];

  for (const [ax, ay] of arduino) {
    let minDist = Infinity;
    let bestMove = [ax, ay];

    for (let d = 1; d <= 9; d++) {
      const nax = ax + dx[d];
      const nay = ay + dy[d];

      if (nax < 0 || nay < 0 || nax >= R || nay >= C) continue;

      const dist = Math.abs(nax - nx) + Math.abs(nay - ny);
      if (dist < minDist) {
        minDist = dist;
        bestMove = [nax, nay];
      }
    }

    const [bx, by] = bestMove;
    tempMap[bx][by] += 1;
  }

  // 종수 위치에 아두이노가 왔는지 확인
  if (tempMap[nx][ny] > 0) {
    console.log(`kraj ${turn + 1}`);
    return;
  }

  // 폭발: 2개 이상인 칸은 제거, 1개만 있는 칸만 유지
  arduino = [];
  for (let i = 0; i < R; i++) {
    for (let j = 0; j < C; j++) {
      if (tempMap[i][j] === 1) {
        arduino.push([i, j]);
      }
    }
  }

  // 새 보드 리셋 후 다시 그리기
  board = Array.from({ length: R }, () => Array(C).fill("."));
  board[nx][ny] = "I";
  for (const [ax, ay] of arduino) {
    board[ax][ay] = "R";
  }
}

// 최종 상태 출력
console.log(board.map((row) => row.join("")).join("\n"));
