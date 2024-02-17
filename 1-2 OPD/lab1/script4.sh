cd lab0
shopt -s globstar
ls -R | grep "0$" | wc -m | sort
ls -Ral |sort -k2 |  grep " m.*$" | tail -n2
cat $(ls -dp1 ./**/* | grep "9$") | sort 2>/dev/null 
ls -Ral | sort -k2 | grep "^-" |  grep "r$" | tail -n2
ls -Ral | sort -k6 | grep "^-" |  grep "do" | head -n4
cat -n $(ls -dp1 ./**/* | grep "r$") | sort -r
