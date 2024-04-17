import socket
import os

# UNIXソケットをストリームモードで作成する
sock = socket.socket(socket.AF_UNIX, socket.SOCK_STREAM)

# UNIXソケットのパスを指定する
server_address = '/tmp/socket_file'

# 以前の接続が残っていた場合に備えて、サーバアドレスをアンリンクする
try:
    os.unlink(server_address)
# 以前の接続が残っていない場合は、何もしない
except FileNotFoundError:
    pass

print('Starting up on {}'.format(server_address))

# サーバアドレスにソケットをバインドする
sock.bind(server_address)

# ソケットを接続待ち状態にする
sock.listen(1)

# 無限ループでクライアントからの接続を待ち続ける
while True:
    connection, client_address = sock.accept()
    try:
        print('connection from', client_address)
        while True:
            data = connection.recv(16)
            # 受け取ったデータを文字列に変換する
            data_str = data.decode('utf-8')
            # 受け取ったデータを表示する
            print('Received ' + data_str)

            if data:
                response = 'Processing ' + data_str
                # 処理したデータをクライアントに送信する
                connection.sendall(response.encode())
            else:
                print('no data from', client_address)
                break
    finally:
        # 接続を閉じる
        print('Closing current connection')
        connection.close()