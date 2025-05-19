const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [kingPos, stonePos, N] = input[0].split(" ");
const moves = input.slice(1);

// 방향 매핑
const directions = {
  R: [1, 0],
  L: [-1, 0],
  B: [0, -1],
  T: [0, 1],
  RT: [1, 1],
  LT: [-1, 1],
  RB: [1, -1],
  LB: [-1, -1],
};

// 체스 위치를 좌표로 변환
function parsePosition(pos) {
  return [pos.charCodeAt(0) - "A".charCodeAt(0), parseInt(pos[1]) - 1];
}

// 좌표를 체스 위치로 변환
function positionToString(pos) {
  return String.fromCharCode(pos[0] + "A".charCodeAt(0)) + (pos[1] + 1);
}

let king = parsePosition(kingPos);
let stone = parsePosition(stonePos);

for (let move of moves) {
  const [dx, dy] = directions[move];
  const nextKing = [king[0] + dx, king[1] + dy];

  // 킹이 체스판을 벗어나는 경우 무시
  if (nextKing[0] < 0 || nextKing[0] > 7 || nextKing[1] < 0 || nextKing[1] > 7)
    continue;

  // 킹이 돌과 겹치는 경우
  if (nextKing[0] === stone[0] && nextKing[1] === stone[1]) {
    const nextStone = [stone[0] + dx, stone[1] + dy];
    if (
      nextStone[0] < 0 ||
      nextStone[0] > 7 ||
      nextStone[1] < 0 ||
      nextStone[1] > 7
    )
      continue;
    stone = nextStone;
  }

  king = nextKing;
}

console.log(positionToString(king));
console.log(positionToString(stone));
