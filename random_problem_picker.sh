#!/bin/bash
# Usage:
#   ./random_problem_picker.sh -l MIN_LEVEL -L MAX_LEVEL -t TAG_LIST -c COUNT [-d]
# Options:
#   -l MIN_LEVEL : 최소 난이도 (default: 8)
#   -L MAX_LEVEL : 최대 난이도 (default: 15)
#   -t TAG_LIST  : 태그 목록 (콤마로 구분, 기본값: "2,1,3,4,5,6,7,8,9,15,17,28")
#   -c COUNT     : 선택할 문제 개수 (default: 3)
#   -d           : 태그 겹침 검사 비활성화 (기본은 태그 겹침 검사 활성화)
#
# 출력 형식:
#   BJ_{oj_problem_number}_{oj_problem_title} {문제_링크}
#
# Requires: curl, jq, awk

# 기본값 설정
MIN_LEVEL=8
MAX_LEVEL=15
COUNT=3
TAG_FILTER="2,1,3,4,5,6,7,8,9,15,17,28"
CHECK_TAGS=1  # 1: 태그 겹침 검사 활성화, 0: 비활성화

# 옵션 파싱 (getopts)
while getopts "l:L:t:c:d" opt; do
    case $opt in
        l) MIN_LEVEL=$OPTARG ;;
        L) MAX_LEVEL=$OPTARG ;;
        t) TAG_FILTER=$OPTARG ;;
        c) COUNT=$OPTARG ;;
        d) CHECK_TAGS=0 ;;  # -d 옵션이 있으면 태그 겹침 검사 비활성화
        *) echo "Usage: $0 -l MIN_LEVEL -L MAX_LEVEL -t TAG_LIST -c COUNT [-d]"; exit 1 ;;
    esac
done

# API 기본 URL 구성 (원래 URL의 쿼리스트링 내용 반영)
API_URL="https://api.services.tony9402.com/problem?oj_name=baekjoon&orderby_column=oj_problem_number&orderby=asc"
# 레벨 조건 추가: 옵션으로 받은 MIN_LEVEL ~ MAX_LEVEL 범위
for (( level=MIN_LEVEL; level<=MAX_LEVEL; level++ )); do
    API_URL+="&levels=${level}"
done
# 기본 태그 목록 추가 (옵션 -t 에 의해 오버라이드 가능)
IFS=',' read -ra TAGS_ARR <<< "$TAG_FILTER"
for tag in "${TAGS_ARR[@]}"; do
    API_URL+="&tags=${tag}"
done
# 추천 조건 추가
API_URL+="&recommend=recommend&recommend=practice"
# 페이징 옵션 추가
API_URL+="&limit=5000&offset=0"

# API 호출하여 JSON 응답 저장
json=$(curl -s "$API_URL")

# jq를 이용해 각 문제의 필요한 정보를 한 줄 JSON으로 추출
# tags: 태그 id들을 콤마로 연결한 문자열로 변환
problems=$(echo "$json" | jq -c '.data[] | {number: .oj_problem_number, title: .oj_problem_title, url: .url, level: .level, tags: (.tags | map(.id) | join(","))}')

# mapfile 대신 while-read 루프를 사용해 배열에 저장
problemArray=()
while IFS= read -r line; do
    problemArray+=("$line")
done <<< "$problems"
totalProblems=${#problemArray[@]}

# 뽑을 문제 개수가 전체 문제 수보다 많으면 종료
if [ "$COUNT" -gt "$totalProblems" ]; then
    echo "Error: 요청한 문제 개수($COUNT)가 전체 문제 수($totalProblems)보다 많습니다."
    exit 1
fi

# 난이도 합 조건 계산: 기대 합 = ((MIN_LEVEL+MAX_LEVEL)/2)*COUNT, 허용 오차 = COUNT/2
EXPECTED_SUM=$(awk -v min="$MIN_LEVEL" -v max="$MAX_LEVEL" -v count="$COUNT" 'BEGIN { printf "%.2f", ((min+max)/2)*count }')
DEVIATION=$(awk -v count="$COUNT" 'BEGIN { printf "%.2f", count/2 }')
LOWER_BOUND=$(awk -v es="$EXPECTED_SUM" -v d="$DEVIATION" 'BEGIN { printf "%.0f", es - d }')
UPPER_BOUND=$(awk -v es="$EXPECTED_SUM" -v d="$DEVIATION" 'BEGIN { printf "%.0f", es + d }')

# 태그 겹침 검사 함수: 두 문제의 태그 문자열(콤마 구분) 중 하나라도 겹치면 return 0 (true)
tags_overlap() {
    local tags1="$1"
    local tags2="$2"
    IFS=',' read -ra arr1 <<< "$tags1"
    IFS=',' read -ra arr2 <<< "$tags2"
    for t1 in "${arr1[@]}"; do
        for t2 in "${arr2[@]}"; do
            if [ "$t1" == "$t2" ]; then
                return 0
            fi
        done
    done
    return 1
}

# 선택한 문제 인덱스들의 조합이 유효한지 확인 (난이도 합 및 태그 겹침 검사)
valid_combination() {
    local sum=0
    local tags_list=()
    local problem level tags
    for idx in "$@"; do
        problem="${problemArray[$idx]}"
        level=$(echo "$problem" | jq '.level')
        tags=$(echo "$problem" | jq -r '.tags')
        sum=$(( sum + level ))
        if [ "$CHECK_TAGS" -eq 1 ]; then
            for t in "${tags_list[@]}"; do
                if tags_overlap "$tags" "$t"; then
                    return 1  # 태그가 겹치면 유효하지 않음
                fi
            done
        fi
        tags_list+=("$tags")
    done
    if [ "$sum" -ge "$LOWER_BOUND" ] && [ "$sum" -le "$UPPER_BOUND" ]; then
        return 0
    else
        return 1
    fi
}

# COUNT개의 서로 다른 인덱스를 무작위로 선택하는 함수
get_random_indices() {
    local n=$1
    local -a indices=()
    while [ ${#indices[@]} -lt "$n" ]; do
        idx=$(( RANDOM % totalProblems ))
        if [[ " ${indices[@]} " =~ " $idx " ]]; then
            continue
        fi
        indices+=("$idx")
    done
    echo "${indices[@]}"
}

# 조건에 맞는 조합을 찾을 때까지 반복 (최대 1000회 시도)
attempt=0
while true; do
    attempt=$((attempt + 1))
    if [ "$attempt" -gt 1000 ]; then
        echo "조건에 맞는 문제 조합을 찾지 못했습니다."
        exit 1
    fi
    indices=($(get_random_indices "$COUNT"))
    if valid_combination "${indices[@]}"; then
        chosen=("${indices[@]}")
        break
    fi
done

# 선택된 문제들을 출력 형식에 맞게 출력
for idx in "${chosen[@]}"; do
    problem="${problemArray[$idx]}"
    number=$(echo "$problem" | jq -r '.number')
    title=$(echo "$problem" | jq -r '.title')
    url=$(echo "$problem" | jq -r '.url')
    echo "BJ_${number}_${title} ${url}"
done
