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

const n = +input.shift();
let minArr = [],
  maxArr = [];
for (const i of input) {
  const [a, b] = i.split(' ').map(Number);
  minArr.push(a - b);
  maxArr.push(a + b);
}

let answer = [];
const sortMin = minArr.slice().sort((a, b) => a - b);
const sortMax = maxArr.slice().sort((a, b) => a - b);

for (let i = 0; i < n; i++) {
  const minNum = minArr[i];
  const maxNum = maxArr[i];

  let min = lowerBound(minNum, sortMax);
  let max = upperBound(maxNum, sortMin);

  answer.push(`${min + 1} ${max + 1}`);
}

console.log(answer.join('\n'));

//최대한 왼쪽으로 이동 (l,r]
function lowerBound(num, arr) {
  let l = -1,
    r = n - 1;

  while (l + 1 < r) {
    let mid = Math.floor((l + r) / 2);
    if (arr[mid] < num) {
      l = mid;
    } else {
      r = mid;
    }
  }

  return r;
}

//최대한 오른쪽으로 이동 [l,r)
function upperBound(num, arr) {
  let l = 0,
    r = n;

  while (l + 1 < r) {
    let mid = Math.floor((l + r) / 2);
    if (arr[mid] <= num) {
      l = mid;
    } else {
      r = mid;
    }
  }

  return l;
}
