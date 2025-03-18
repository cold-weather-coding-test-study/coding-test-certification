const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");

// N : 4, M : 2
const [N, M] = input.map((v) => parseInt(v, 10));

let result = [];
let visited = new Array(N + 1).fill(false);
let sequence = [];

function backtrack(depth) {
  // 재귀 종료
  if (depth === M) { // M=2
    // depth === 1 !== M
    // depth === 2 === M, i = 2
    // depth === 2 === M, i = 3
    // depth === 2 === M, i = 4
    result.push(sequence.join(" "));
    // depth === 2, i = 2, result = [1 2]
    // depth === 2, i = 3, result = [1 2, 1 3]
    // depth === 2, i = 4, result = [1 2, 1 3, 1 4]
    return;
  }

  //
  for (let i = 1; i <= N; i++) { // N=4
    if (!visited[i]) { 
      // i = 1
      // backtrack(1)일 때 !visited[1](=!true) !== true
      // i = 2
      // i = 3
      // i = 4
      visited[i] = true; 
      // visited[1] = [false, true, false, false, false]
      // visited[2] = [false, true, true, false, false]
      // visited[3] = [false, true, false, true, false]
      // visited[4] = [false, true, false, false, true]
      sequence.push(i); 
      // sequence = [1] 
      // sequence = [1, 2]
      // sequence = [1, 3]
      // sequence = [1, 4]
      backtrack(depth + 1); 
      // backtrack(0 + 1) -> function backtrack(1) 시작, 여기서 sequence.pop() 함수로 넘어X
      // backtrack(1 + 1) -> function backtrack(2) 시작
      // backtrack(1 + 1) -> function backtrack(2) 시작
      // backtrack(1 + 1) -> function backtrack(2) 시작
      sequence.pop(); 
      // sequence = [1, 2] -> [1]
      // sequence = [1, 3] -> [1]
      // sequence = [1, 4] -> [1]
      // seqence = [1] -> [], 하고 seqence[2]로 시작 & 반복
      visited[i] = false;
      // visite[2] = [false, true, true, false, false] -> [false, true, false, false, false]
      // visite[2] = [false, true, false, true, false] -> [false, true, false, flase, false]
      // visite[2] = [false, true, false, false, true] -> [false, true, false, flase, false]
      // 여기까지가 sequence = [1 N] 일 때 재귀, 다음은 sequence.pop() 함수로 넘어감
    }
  }
}

backtrack(0);
console.log(result.join("\n"));4