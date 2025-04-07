function findLen(y, b) {
  for (let i = 1; i <= Math.sqrt(y); i++) {
    if (y % i === 0) {
      let yellow = [y / i, i];
      let brown = (yellow[0] + 2) * (yellow[1] + 2);

      if (brown === b) {
        console.log(i, yellow[1] + 2);
        return [yellow[0] + 2, yellow[1] + 2];
      }
    }
  }
}

function solution(brown, yellow) {
  var answer = [];
  answer = findLen(yellow, yellow + brown);
  return answer;
}
