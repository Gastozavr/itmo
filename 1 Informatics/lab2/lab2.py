string=input()
symbols=['r1', 'r2','i1','r3','i2','i3','i4']
if len(string)!=7 or bool(set(string)-{'1','0'}):
    print("Строка должна быть набором из 7 символов и содержать только 0 или 1")
else:
    array= list(map(int,list(string)))
    s1= (array[0] + array[2] + array[4] + array[6])%2
    s2= (array[1] + array[2] + array[5] + array[6])%2
    s3= (array[3] + array[4] + array[5] + array[6])%2
    syndrome=(s1,s2,s3)
    if syndrome != (0,0,0):
        num=int(''.join(map(str,syndrome[::-1])),2)
        print("Найдена ошибка в символе", symbols[num-1])
        array[num-1]=1-array[num-1]
        rez=''.join(map(str,array))
        print(f"Правильное сообщение: {rez[2]}{rez[4]}{rez[5]}{rez[6]}")

        
