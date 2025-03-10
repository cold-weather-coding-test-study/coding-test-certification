const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, m] = input[0].split(' ').map(Number);
//n까지의 수 중 m개를 고른다. -> 백트래킹
//중복 허용하지 않음 -> 방문체크

const arr = [];
for (let i = 1; i <= n; i++) {
  arr.push(i);
}

const visited = Array(n + 1).fill(false);

function dfs(num, len) {
  if (len === m) {
    console.log(num);
    return;
  }

  for (let i = 0; i < arr.length; i++) {
    if (!visited[arr[i]]) {
      visited[arr[i]] = true;
      dfs(num + (arr[i] + ' '), len + 1);
      visited[arr[i]] = false;
    }
  }
}

dfs('', 0);