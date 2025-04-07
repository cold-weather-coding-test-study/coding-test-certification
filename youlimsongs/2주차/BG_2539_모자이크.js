const path = require('path');
const filePath =
  process.platform === 'linux'
    ? '/dev/stdin'
    : path.join(__dirname, 'input.txt');
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [r, c] = input.shift().split(' ').map(Number);
const paperCnt = +input.shift();
const wrongPaintCnt = +input.shift();

let maxH = 0;
const wrongPaint = [];

for (let i = 0; i < wrongPaintCnt; i++) {
  const [a, b] = input.shift().split(' ').map(Number);
  maxH = Math.max(maxH, a);
  wrongPaint.push([a, b]);
}

wrongPaint.sort((p1, p2) => p1[1] - p2[1]);

let left = maxH;
let right = 1000001;
let ans = right;

while (left <= right) {
  const mid = Math.floor((left + right) / 2);
  let paperCntTmp = 1;
  let columnTmp = wrongPaint[0][1];

  for (let i = 1; i < wrongPaintCnt; i++) {
    if (wrongPaint[i][1] >= columnTmp + mid) {
      paperCntTmp++;
      columnTmp = wrongPaint[i][1];
    }
  }

  if (paperCntTmp <= paperCnt) {
    ans = mid;
    right = mid - 1;
  } else {
    left = mid + 1;
  }
}

console.log(ans);
