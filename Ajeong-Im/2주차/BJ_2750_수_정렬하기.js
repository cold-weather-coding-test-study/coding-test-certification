function solve(input) {
  const lines = input.trim().split("\n");
  const N = parseInt(lines[0], 10);
  const arr = [];
  for (let i = 1; i <= N; i++) {
    arr.push(parseInt(lines[i], 10));
  }
  arr.sort((a, b) => a - b);
  return arr.join("\n");
}

if (typeof process !== "undefined" && process.stdin) {
  let input = "";
  process.stdin.on("data", (chunk) => (input += chunk));
  process.stdin.on("end", () => console.log(solve(input)));
}
