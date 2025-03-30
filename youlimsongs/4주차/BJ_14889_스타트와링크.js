const path = require('path');
const filePath =
  process.platform === 'linux'
    ? '/dev/stdin'
    : path.join(__dirname, 'input.txt');
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const n = +input.shift();
const map = [];
while (input.length) {
  map.push(input.shift().split(' ').map(Number));
}

let answer = Infinity;

function calPower(arr) {
  let teamPower = 0;
  for (let i = 0; i < arr.length; i++) {
    for (let j = i + 1; j < arr.length; j++) {
      let a = arr[i];
      let b = arr[j];
      teamPower += map[a][b] + map[b][a];
    }
  }

  return teamPower;
}

function dfs(idx, team) {
  if (team.length === n / 2) {
    //상대 팀 계산
    const otherTeam = [];
    for (let i = 0; i < n; i++) {
      if (!team.includes(i)) otherTeam.push(i);
    }

    const t1 = calPower(team);
    const t2 = calPower(otherTeam);
    answer = Math.min(answer, Math.abs(t1 - t2));
    return;
  }

  for (let i = idx + 1; i < n; i++) {
    team.push(i);
    dfs(i, team);

    team.pop();
  }
}

dfs(0, [0]);

console.log(answer);
