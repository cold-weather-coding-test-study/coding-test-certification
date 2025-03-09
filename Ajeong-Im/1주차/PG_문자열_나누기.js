function solution(s) {
  let count = 0;
  let index = 0;

  while (index < s.length) {
    const x = s[index];
    let countX = 0;
    let countOther = 0;
    let j = index;

    while (j < s.length) {
      if (s[j] === x) countX++;
      else countOther++;
      j++;
      if (countX === countOther) break;
    }
    count++;
    index = j;
  }
  return count;
}
