function solve(N, X) {
  X.sort((a, b) => a - b);
  const S = Array(2 * N).fill(null);
  const used = Array(N).fill(false);

  function backtrack(pos) {
    if (pos === 2 * N) return true;

    if (S[pos] !== null) return backtrack(pos + 1);

    for (let i = 0; i < N; i++) {
      const x = X[i];
      const j = pos + x + 1;

      if (!used[i] && j < 2 * N && S[j] === null) {
        S[pos] = x;
        S[j] = x;
        used[i] = true;

        if (backtrack(pos + 1)) return true;

        S[pos] = null;
        S[j] = null;
        used[i] = false;
      }
    }

    return false;
  }

  if (backtrack(0)) {
    console.log(S.join(" "));
  } else {
    console.log(-1);
  }
}

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const N = Number(input[0]);
const X = input[1].split(" ").map(Number);
solve(N, X);
