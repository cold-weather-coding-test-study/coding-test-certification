const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, m] = input[0].split(' ').map(Number);
const num = Array.from({ length: n }, (_, i) => i + 1);

function dfs(cur, start) {
  if (cur.length > m) return;

  if (cur.length === m) {
    console.log(cur.join(' '));
    return;
  }

  for (let i = start; i < n; i++) {
    dfs([...cur, num[i]], i + 1);
  }
}

dfs([], 0);
