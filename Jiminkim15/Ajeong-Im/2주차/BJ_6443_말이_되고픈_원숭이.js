function solve(input) {
  const tokens = input.trim().split(/\s+/).map(Number);
  let idx = 0;
  const K = tokens[idx++];
  const W = tokens[idx++];
  const H = tokens[idx++];
  const grid = [];
  for (let i = 0; i < H; i++) {
    grid.push(tokens.slice(idx, idx + W));
    idx += W;
  }
  if (H === 1 && W === 1) return "0";
  const visited = Array.from({ length: H }, () =>
    Array.from({ length: W }, () => Array(K + 1).fill(false))
  );
  const queue = [];
  queue.push({ x: 0, y: 0, k: 0, steps: 0 });
  visited[0][0][0] = true;
  const horseMoves = [
    [-2, -1],
    [-1, -2],
    [1, -2],
    [2, -1],
    [2, 1],
    [1, 2],
    [-1, 2],
    [-2, 1],
  ];
  const adjMoves = [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
  ];
  while (queue.length) {
    const { x, y, k, steps } = queue.shift();
    if (x === H - 1 && y === W - 1) return steps.toString();
    for (const [dx, dy] of adjMoves) {
      const nx = x + dx,
        ny = y + dy;
      if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
      if (grid[nx][ny] === 1 || visited[nx][ny][k]) continue;
      visited[nx][ny][k] = true;
      queue.push({ x: nx, y: ny, k: k, steps: steps + 1 });
    }
    if (k < K) {
      for (const [dx, dy] of horseMoves) {
        const nx = x + dx,
          ny = y + dy;
        if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
        if (grid[nx][ny] === 1 || visited[nx][ny][k + 1]) continue;
        visited[nx][ny][k + 1] = true;
        queue.push({ x: nx, y: ny, k: k + 1, steps: steps + 1 });
      }
    }
  }
  return "-1";
}

if (typeof process !== "undefined" && process.stdin) {
  let input = "";
  process.stdin.on("data", (chunk) => (input += chunk));
  process.stdin.on("end", () => console.log(solve(input)));
}
