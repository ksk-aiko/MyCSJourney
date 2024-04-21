import socket
import os

# UNIXソケットを作成する
sock = socket.socket(socket.AF_UNIX, socket.SOCK_DGRAM)

# サーバアドレスを指定する
server_address = 'sockets/socket_file'

# クライアントアドレスを指定する
client_address = 'sockets/client_socket_file'


# 以前の接続が残っていた場合に備えて、クライアントアドレスをアンリンクする
try:
    os.unlink(client_address)
except FileNotFoundError:
    pass

# ソケットをバインドする
sock.bind(client_address)

# サーバからのデータが30秒間受信されない場合、メッセージを表示して、ソケットをクローズする
sock.settimeout(30)

# サーバアドレスにメッセージを送信する
try:
    while True:
        # ユーザーの入力を受け取る
        message = input('Enter message: ')

        print('Sending {!r}'.format(message))
        sent = sock.sendto(message.encode(), server_address)
        if message == 'exit':
            break
        print('waiting to receive')
        data, server = sock.recvfrom(4096)
        print('received {!r}'.format(str(data)) + ' from ' + str(server))

finally:
    print('closing socket')
    sock.close()
