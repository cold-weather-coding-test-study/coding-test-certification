//https://school.programmers.co.kr/learn/courses/30/lessons/43162

function solution(n, computers) {
  var answer = 0;
  let visited = Array(n).fill(false);

  for (let i = 0; i < n; i++) {
    if (visited[i]) continue;

    dfs(i);
    answer++;
  }

  function dfs(i) {
    visited[i] = true;

    for (let j = 0; j < n; j++) {
      if (i === j) continue;
      if (computers[i][j] && !visited[j]) {
        visited[j] = true;
        dfs(j);
      }
    }
  }

  return answer;
}
