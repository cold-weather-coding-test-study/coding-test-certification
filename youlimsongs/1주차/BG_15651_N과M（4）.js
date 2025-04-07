const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, m] = input[0].split(' ').map(Number);
const nums = Array.from({ length: n }, (_, i) => i + 1);

function dfs(arr, len) {
  if (len > m) return;
  if (len === m) {
    console.log(arr.slice(1).join(' '));
    return;
  }

  for (const a of nums) {
    if (arr[arr.length - 1] <= a) {
      dfs([...arr, a], len + 1);
    }
  }
}

dfs([0], 0);
