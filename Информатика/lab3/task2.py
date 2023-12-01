import re
pattern = r'(\w+)(\s+\1\b)+'
string=input()
print(re.sub(pattern, '\\1', string))
