// https://www.acmicpc.net/problem/15810
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

const [n, m] = input[0].split(' ').map(Number);
const time = input[1].split(' ').map(Number);
time.sort((a, b) => a - b);

let l = 0;
let r = m * time[0]; //M=10, [1, 100] -> 최대 r = 10 * 1

while (l + 1 < r) {
  let mid = Math.floor((l + r) / 2);
  let ball = 0;

  for (let i = 0; i < n; i++) {
    ball += Math.floor(mid / time[i]);
  }

  if (ball >= m) {
    r = mid;
  } else {
    l = mid;
  }
}

console.log(r);
