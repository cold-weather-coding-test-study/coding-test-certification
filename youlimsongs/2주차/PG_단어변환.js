// https://school.programmers.co.kr/learn/courses/30/lessons/43163

function solution(begin, target, words) {
  let answer = 100;
  let visited = Array(words.length).fill(false);

  function dfs(n, cnt) {
    if (n === target) {
      answer = Math.min(cnt, answer);
      return;
    }

    for (let i = 0; i < words.length; i++) {
      let word = words[i];

      if (!visited[i]) {
        //n과 하나만 다른 단어 찾기
        let dif = 0;
        for (let j = 0; j < word.length; j++) {
          if (word[j] !== n[j]) {
            dif++;
            if (dif > 1) break;
          }
        }
        if (dif !== 1) continue;

        visited[i] = true;
        dfs(word, cnt + 1);

        visited[i] = false;
      }
    }
    return;
  }

  dfs(begin, 0);

  return answer === 100 ? 0 : answer;
}
