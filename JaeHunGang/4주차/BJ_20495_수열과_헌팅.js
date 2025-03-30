// https://www.acmicpc.net/problem/20495
const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const ranges = []; // [min, max] 범위를 저장

for (let i = 1; i <= N; i++) {
  const [a, b] = input[i].split(' ').map(Number);
  ranges.push([a - b, a + b]);
}

// 정렬용 배열 만들기
const allMins = ranges.map(([minVal]) => minVal).sort((a, b) => a - b);
const allMaxs = ranges.map(([_, maxVal]) => maxVal).sort((a, b) => a - b);

/**
 * lowerBound: target 이상인 첫 위치
 * ex) [1, 3, 5], target = 4 → return 2 (index of 5)
 */
function lowerBound(arr, target) {
  let left = 0, right = arr.length;
  while (left < right) {
    const mid = Math.floor((left + right) / 2);
    if (arr[mid] < target) left = mid + 1;
    else right = mid;
  }
  return left;
}

/**
 * upperBound: target 초과인 첫 위치
 * ex) [1, 3, 5], target = 3 → return 2 (index of 5)
 */
function upperBound(arr, target) {
  let left = 0, right = arr.length;
  while (left < right) {
    const mid = Math.floor((left + right) / 2);
    if (arr[mid] <= target) left = mid + 1;
    else right = mid;
  }
  return left;
}

// 결과 출력
const result = [];
for (const [minRange, maxRange] of ranges) {
  const minIndex = lowerBound(allMaxs, minRange) + 1; // 1-based index
  const maxIndex = upperBound(allMins, maxRange);     // 1-based index
  result.push(`${minIndex} ${maxIndex}`);
}

console.log(result.join('\n'));