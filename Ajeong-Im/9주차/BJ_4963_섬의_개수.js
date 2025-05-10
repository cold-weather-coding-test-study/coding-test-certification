const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let index = 0;
const dx = [-1, -1, -1, 0, 0, 1, 1, 1];
const dy = [-1, 0, 1, -1, 1, -1, 0, 1];

while (true) {
  const [w, h] = input[index++].split(" ").map(Number);
  if (w === 0 && h === 0) break;

  const map = [];
  for (let i = 0; i < h; i++) {
    map.push(input[index++].split(" ").map(Number));
  }

  const visited = Array.from({ length: h }, () => Array(w).fill(false));

  function dfs(x, y) {
    visited[x][y] = true;
    for (let d = 0; d < 8; d++) {
      const nx = x + dx[d];
      const ny = y + dy[d];
      if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
        if (!visited[nx][ny] && map[nx][ny] === 1) {
          dfs(nx, ny);
        }
      }
    }
  }

  let count = 0;
  for (let i = 0; i < h; i++) {
    for (let j = 0; j < w; j++) {
      if (map[i][j] === 1 && !visited[i][j]) {
        dfs(i, j);
        count++;
      }
    }
  }

  console.log(count);
}
