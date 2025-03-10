function solution(word) {
  var answer = 0;
  const string = ['A', 'E', 'I', 'O', 'U'];
  const dic = [];

  function dfs(str, len) {
    if (len > 5) return;
    dic.push(str);

    for (let i = 0; i < string.length; i++) {
      dfs(str + string[i], len + 1);
    }
  }

  dfs('', 0);

  return dic.indexOf(word);
}
