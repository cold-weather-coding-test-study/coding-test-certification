const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [rows, cols] = input[0].split(" ").map(Number);
const nConfetties = Number(input[1]);
const nWrongSpaces = Number(input[2]);

const wrongSpaces = input.slice(3).map((line) => {
  const [r, c] = line.split(" ").map(Number);
  return { r, c };
});

// 좌표를 row -> col 기준으로 정렬 (왼쪽 위부터 덮기 위해)
wrongSpaces.sort((a, b) => (a.r - b.r) || (a.c - b.c));

let start = 1;
let end = Math.max(rows, cols);
let result = -1;

const canCover = (size) => {
  let usedConfetties = 0;
  const covered = Array(nWrongSpaces).fill(false);

  for (let i = 0; i < nWrongSpaces; i++) {
    if (!covered[i]) {
      usedConfetties++;

      // 색종이의 시작점을 도화지 범위를 넘지 않게 보정
      const baseRow = Math.min(wrongSpaces[i].r, rows - size);
      const baseCol = Math.min(wrongSpaces[i].c, cols - size);
      const endRow = baseRow + size - 1;
      const endCol = baseCol + size - 1;

      // 해당 색종이로 덮을 수 있는 잘못된 칸을 모두 덮기
      for (let j = 0; j < nWrongSpaces; j++) {
        if (!covered[j] &&
            wrongSpaces[j].r >= baseRow && wrongSpaces[j].r <= endRow &&
            wrongSpaces[j].c >= baseCol && wrongSpaces[j].c <= endCol) {
          covered[j] = true;
        }
      }
    }
  }

  return usedConfetties <= nConfetties;
};

// 이분 탐색으로 최소 색종이 크기 찾기
while (start <= end) {
  const mid = Math.floor((start + end) / 2);

  if (canCover(mid)) {
    result = mid;
    end = mid - 1;
  } else {
    start = mid + 1;
  }
}

console.log(result);