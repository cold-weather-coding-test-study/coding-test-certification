// Queue! (FIFO : First In, First Out)
function queue() {
  const [num, ...cmdArr] = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
  const que = [];
  const result = [];

  const cmdObj = {
    push: X => { que.push(X); },
    pop: () => { return que.length === 0 ? -1 : que.shift(); },
    size: () => { return que.length; },
    empty: () => { return que.length === 0 ? 1 : 0; },
    front: () => { return que.length === 0 ? -1 : que[0]; },
    back: () => { return que.length === 0 ? -1 : que[que.length - 1]; }
  }

  for (let i = 0; i < num; i++) {
    let [cmd, intNum] = cmdArr[i].split(" ");

    if (cmd === "push") cmdObj.push(parseInt(intNum));
    else result.push(cmdObj[cmd]());
  }

  console.log(result.join("\n"));
}

queue();