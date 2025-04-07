function solution(n, wires) {
  var answer = 100;

  for (let i = 0; i < wires.length; i++) {
    const arr = Array.from({ length: n + 1 }, () => []);

    //한 연결씩 잘라서 연결 구조 초기화
    wires.forEach((w, idx) => {
      if (idx === i) return;
      arr[w[0]].push(w[1]);
      arr[w[1]].push(w[0]);
    });

    //탐색
    const visited = Array(n + 1).fill(false);
    function dfs(idx) {
      visited[idx] = true;

      for (let i = 0; i < arr[idx].length; i++) {
        const next = arr[idx][i];
        if (!visited[next]) {
          visited[next] = true;
          dfs(next);
        }
      }
    }

    dfs(wires[i][1]);

    const link = visited.filter((v) => v === true).length;

    answer = Math.min(answer, Math.abs(link - (n - link)));
  }
  return answer;
}
