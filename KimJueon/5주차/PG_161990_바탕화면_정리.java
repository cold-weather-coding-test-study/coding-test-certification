class PG_161990_바탕화면_정리 {

    public int[] solution(String[] wallpaper) {
        int lmin=Integer.MAX_VALUE,lmax=0;
        int rmin=Integer.MAX_VALUE,rmax=0;

        for(int i=0 ; i<wallpaper.length;i++){
            for(int j=0 ; j<wallpaper[i].length();j++){
                if(wallpaper[i].charAt(j)=='#'){
                    lmin = Math.min(lmin,i);
                    lmax = Math.max(lmax,i);
                    rmin = Math.min(rmin,j);
                    rmax = Math.max(rmax,j);
                }
            }
        }

        int[] answer = {lmin,rmin,lmax+1,rmax+1};
        return answer;
    }
}
