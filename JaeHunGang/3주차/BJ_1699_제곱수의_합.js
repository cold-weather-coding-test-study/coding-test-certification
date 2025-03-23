const input = require("fs").readFileSync("/dev/stdin").toString().trim();

// i는 항의 최소 개수를 구하려는 자연수
// dp[i]는 현재 항의 최소 갯수
// 경우는 i를 만들 수 있는 식
// 식은 제곱수들의 합의 조합
// 항의 갯수는 식의 제곱수 갯수
const dp = Array(parseInt(input, 10) + 1).fill(Infinity);
dp[0] = 0;

for (let i = 1; i <= input; i++) {
  for (let j = 1; j * j <= i; j++) {
    dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // if(i -j^2 > 0) i = i - j^2 + 1
  }
}

console.log(dp[input]);
