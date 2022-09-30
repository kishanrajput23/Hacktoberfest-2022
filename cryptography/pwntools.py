from pwn import * # pip install pwntools
import json
from Crypto.Util.number import bytes_to_long, long_to_bytes
import base64
import codecs
import random
from binascii import unhexlify

r = remote('socket.cryptohack.org', 13377, level = 'debug')

def json_recv():
    line = r.recvline()
    return json.loads(line.decode())

def json_send(hsh):
    request = json.dumps(hsh).encode()
    r.sendline(request)

def list_to_string(s):
    output = ""
    return(output.join(s))

for i in range(0,101):
    received = json_recv()
    if "flag" in received:
        print("\n[*] FLAG : {}".format(received["flag"]))
        break
        
    print("\n[-] Cycle: {}".format(i))
    print("[-] Received type: {}".format(received["type"]))
    print("[-] Received encoded value: {}".format(received["encoded"]))

    word = received["encoded"]
    encoding = received["type"]

    if encoding == "base64":
        decoded = base64.b64decode(word).decode('utf8').replace("'", '"')
    elif encoding == "hex":
        decoded = (unhexlify(word)).decode('utf8').replace("'", '"')
    elif encoding == "rot13":
        decoded = codecs.decode(word, 'rot_13')
    elif encoding == "bigint":
        decoded = unhexlify(word.replace("0x", "")).decode('utf8').replace("'", '"')
    elif encoding == "utf-8":
        decoded = list_to_string([chr(b) for b in word])

    print("[-] Decoded: {}".format(decoded))
    print("[-] Decoded Type: {}".format(type(decoded)))

    to_send = {
        "decoded": decoded
    }

    json_send(to_send)