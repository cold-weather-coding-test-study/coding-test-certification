const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line.trim());
}).on("close", () => {
  const [N, M, A, B] = input[0].split(" ").map(Number);
  const items = input.slice(1, 1 + A).map((l) => l.split(" ").map(Number));
  const blocks = input.slice(1 + A).map((l) => l.split(" ").map(Number));

  function countPaths(N, M, items, blocks) {
    let map = Array.from({ length: N + 1 }, () => Array(M + 1).fill(0));

    for (const [x, y] of blocks) {
      map[x][y] = -1;
    }

    let points = [
      [1, 1],
      ...items.sort((a, b) => {
        if (a[0] === b[0]) return a[1] - b[1];
        return a[0] - b[0];
      }),
      [N, M],
    ];

    let totalPaths = 1;

    for (let i = 0; i < points.length - 1; i++) {
      const [sx, sy] = points[i];
      const [ex, ey] = points[i + 1];

      let dp = Array.from({ length: N + 1 }, () => Array(M + 1).fill(0));
      dp[sx][sy] = 1;

      for (let x = sx; x <= ex; x++) {
        for (let y = sy; y <= ey; y++) {
          if (map[x][y] === -1 || (x === sx && y === sy)) continue;
          dp[x][y] = (dp[x - 1]?.[y] || 0) + (dp[x][y - 1] || 0);
        }
      }

      totalPaths *= dp[ex][ey];
      if (totalPaths === 0) return 0;
    }

    return totalPaths;
  }

  console.log(countPaths(N, M, items, blocks));
});
