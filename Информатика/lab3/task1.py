#409909 % 6 = 1 => Глаза: ;
#409909 % 4 = 1 => Рот: <
#409909 % 7 = 3 => Нос: |
#Смайлик ;<|
import re
pattern = r';<\|'
string=input()
print(len(re.findall(pattern, string)))

