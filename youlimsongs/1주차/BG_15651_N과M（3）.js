const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, m] = input[0].split(' ').map(Number);

let arr = [];
let result = '';

function dfs(len) {
  if (len > m) return;

  if (len === m) {
    result += `${arr.join(' ')}\n`;
    return;
  }

  for (let i = 1; i <= n; i++) {
    arr.push(i);
    dfs(len + 1);
    arr.pop();
  }
}

dfs(0);
console.log(result);