function solution(numbers, target) {
    var answer = 0;
    let total =0;
    
    //순서는 상관이 없음. -,+ 순서만 중요.
    const cal = [1, -1];
    
    function dfs(idx){
        if(idx === numbers.length){
            if(total===target) answer += 1;
            return;
        }
        
        for(let c of cal){
            total += numbers[idx] * c;
            dfs(idx+1);
            
            total -= numbers[idx] * c;
        }
    }
    
    
    dfs(0);
   return answer;
}