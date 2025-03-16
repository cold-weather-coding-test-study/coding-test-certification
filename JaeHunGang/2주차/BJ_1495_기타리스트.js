const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, S, M] = input[0].split(" ").map((v) => parseInt(v, 10));
const V = input[1].split(" ").map((v) => parseInt(v, 10));

let maxLastSongVol = -1; // 마지막 곡을 연주 가능한 볼륨이 없을 경우

// DP 배열 초기화 (index = 0 부터 시작, 볼륨 = 0, 부터 시작)
let dp = Array.from({ length: N + 1 }, () => Array(M + 1).fill(false));

// 시작 볼륨
dp[0][S] = true;

// DP 수행
for (let i = 0; i < N; i++) {
  for (let v = 0; v <= M; v++) {
    if (dp[i][v]) {
      // dp[0][S]만 true => dp[0][S] 에서 시작
      if (v + V[i] <= M) dp[i + 1][v + V[i]] = true;
      if (v - V[i] >= 0) dp[i + 1][v - V[i]] = true;
    }
  }
}

// 마지막 곡에서 가증한 볼륨 최댓값 찾기
for(let v=M; v>=0; v--){
  if(dp[N][v]) { // M 값부터 내려오면서 계산
    maxLastSongVol = v;
    break;
  }
}

console.log(maxLastSongVol);