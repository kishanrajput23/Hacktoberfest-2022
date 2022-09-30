data = "label"
flag = ''

for c in data:
    flag += chr(ord(c) ^ 13)

print('crypto{{{}}}'.format(flag))