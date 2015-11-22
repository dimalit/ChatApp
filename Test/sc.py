import socket
s = socket.socket()
f = open('command.txt','r')
s.connect((str(input()), 28411))
cmd = f.readline()
while cmd != '':
    s.send(cmd.encode())
    cmd = f.readline()
s.close()
f.close()
