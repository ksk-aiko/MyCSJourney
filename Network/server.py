import socket
import os
from pathlib import Path

# socketオブジェクトを作成
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# 任意のIPアドレスから接続を受け入れる
server_address = '0.0.0.0'
sever_port = 9001

# 現在の作業ディレクトリにtempフォルダが存在するかをチェック
depth = 'temp'
if not os.path.exists(depth):
    # 存在しない場合は作成する。クライアントからのファイルを受け取るためのフォルダ
    os.makedirs(depth)

print('Starting up on {} port {}'.format(server_address, sever_port))

# ソケットを指定したIPアドレスとポートにバインド
sock.bind((server_address, sever_port))
# 接続を待ち受ける
sock.listen(1)

while True:
    connection, client_address = sock.accept()
    try:
        print('connection from', client_address)
        # クライアントから受信したデータのヘッダを読み取り、変数に格納する
        # ファイル名の長さ、JSONデータの長さ、クライアントから受信するデータの長さを取得する
        header = connection.recv(8)

        filename_length = int.from_bytes(header[:1], "big")
        json_length = int.from_bytes(header[1:3], "big")
        data_length = int.from_bytes(header[4:8], "big")
        stream_rate = 4096

        print('Received header from client. Byte length: Title length {}, JSON length {}, Data length {}'.format(filename_length, json_length, data_length))

        # ファイル名を読み取り、変数に格納する
        filename = connection.recv(filename_length).decode('utf-8')

        print('Filename: {}'.format(filename))

        # JSONデータがある場合は、例外を発生させる
        if json_length != 0:
            raise Exception('JSON data is not currently supported.')
        
        if data_length == 0:
            raise Exception('No data to read from client.')

        with open(os.path.join(depth, filename), 'wb+') as f:
            while data_length > 0:
                data = connection.recv(data_length if data_length <= stream_rate else stream_rate)
                f.write(data)
                print('recieved {} bytes'.format(len(data)))
                data_length -= len(data)
                print(data_length)

            print('Finished downloading the file from client.')

    except Exception as e:
            print('Error:', str(e))

    finally:
            print('Closing current connection')
            connection.close()
