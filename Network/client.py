import socket
import os
import sys

# サーバーに送信されるヘッダ情報をフォーマットする、protocol_header関数を作成
def protocol_header(filename_length, json_length, data_length):
    # ファイル名の長さ、JSONデータの長さ、データの長さをバイト列に変換する
    return filename_length.to_bytes(1, "big") + json_length.to_bytes(3, "big") + data_length.to_bytes(4, "big")

# ソケットオブジェクトを作成
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# サーバーが待ち受けているポートに接続する
server_address = input("Type in the server's address to connect to: ")
server_port = 9001

print('connecting to {}'.format(server_address, server_port))

# 接続に成功したら、サーバーとクライアントが相互に読み書きできるようになる
try:
    sock.connect((server_address, server_port))
except socket.error as err:
    print(err)
    sys.exit(1)

try:
    # ファイルを送信する場合、テキストファイルで2GB以下に制限する
    filepath = input('Type in a file to upload: ')
    # バイナリーモードでファイルを開く
    with open(filepath, 'rb') as f:
        f.seek(0, os.SEEK_END)
        filesize = f.tell()
        f.seek(0, 0)

        if filesize > pow(2, 32):
            raise Exception('File size must be below 2GB.')

        filename = os.path.basename(f.name)

        # ファイル名をビット数に変換
        filename_bits = filename.encode('utf-8')

        # ヘッダ情報を作成
        header = protocol_header(len(filename_bits), 0, filesize)

        # ヘッダ情報をサーバーに送信
        sock.send(header)

        # ファイル名をサーバーに送信
        sock.send(filename_bits)

        # 一度に4096バイトずつ読み出し、送信する
        data = f.read(4096)
        while data:
            print("Sending...")
            sock.send(data)
            data = f.read(4096)

finally:
    print('closing socket')
    sock.close()

