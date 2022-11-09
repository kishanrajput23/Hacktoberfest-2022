a = int(input())
b = int(input())

if a>b:
    while(a%b)!=0:
        rem = a%b
        a = b
        b = rem
    print(b)
else:
    while b%a!=0:
        rem = b%a
        b = a
        a = rem
    print(a)
