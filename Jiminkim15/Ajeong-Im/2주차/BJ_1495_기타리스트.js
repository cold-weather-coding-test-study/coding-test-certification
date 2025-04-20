function solve(input) {
  const lines = input.trim().split("\n");
  const [N, S, M] = lines[0].split(" ").map(Number);
  const volumeChanges = lines[1].split(" ").map(Number);
  const dp = Array.from({ length: N + 1 }, () => Array(M + 1).fill(false));
  dp[0][S] = true;

  for (let i = 0; i < N; i++) {
    for (let v = 0; v <= M; v++) {
      if (dp[i][v]) {
        const plus = v + volumeChanges[i];
        if (plus <= M) dp[i + 1][plus] = true;
        const minus = v - volumeChanges[i];
        if (minus >= 0) dp[i + 1][minus] = true;
      }
    }
  }

  let answer = -1;
  for (let v = 0; v <= M; v++) {
    if (dp[N][v]) answer = Math.max(answer, v);
  }

  return answer.toString();
}

if (typeof process !== "undefined" && process.stdin) {
  let input = "";
  process.stdin.on("data", (chunk) => (input += chunk));
  process.stdin.on("end", () => console.log(solve(input)));
}
