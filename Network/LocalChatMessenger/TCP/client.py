import socket
import sys

# ソケットを作成する
sock = socket.socket(socket.AF_UNIX, socket.SOCK_STREAM)

# サーバが待ち受けている場所にソケットを接続する
server_address = '/tmp/socket_file'
print('connecting to {}'.format(server_address))

# サーバに接続を試みる。問題があった場合は、エラーメッセージを表示して終了する
try:
    sock.connect(server_address)
except socket.error as err:
    print(err)
    # プログラムを終了する
    sys.exit(1)

# サーバに接続できた場合は、サーバにメッセージを送信する
try:
    message = b'Sending a message to the server side'
    sock.sendall(message)
    # サーバからのレスポンスを待つ時間を２秒間に指定する
    sock.settimeout(2)

    # サーバからのレスポンスがある場合は、それを表示する
    try:
        while True:
            data = str(sock.recv(32))

            if data:
                print('Server response: {}'.format(data))
            else:
                break
    # 応答がない場合は、エラーメッセージを表示する
    except(TimeoutError):
        print('Socket timeout, ending listening for server messages')

finally:
    print('closing socket')
    sock.close()