import socket
import os
from faker import Faker

# UNIXソケットを作成する
sock = socket.socket(socket.AF_UNIX, socket.SOCK_DGRAM)

# サーバアドレスを指定する
server_address = 'sockets/socket_file'

# 以前の接続が残っていた場合に備えて、サーバアドレスをアンリンクする
try:
    os.unlink(server_address)
except:
    pass

print('Starting up on {}'.format(server_address))

# サーバアドレスにバインドする
sock.bind(server_address)

# クライアントからのデータが30秒間受信されない場合、メッセージを表示して、ソケットをクローズする
sock.settimeout(30)

# メッセージを受信する
while True:
    print('\nwaiting to receive message')
    data, address = sock.recvfrom(4096)
    print('received {} bytes from {}'.format(len(data), address))
    
    if data:
        message = data.decode()
        # 受信したデータをデコードして表示する
        print('This data is...{!r}'.format(message))
        if message == 'exit':
            break
        # データを加工する
        fake = Faker()
        fake_message = fake.text()
        print('New message is ...{!r}'.format(fake_message))
        # クライアントにメッセージを返信する
        print(address)
        sent = sock.sendto(fake_message.encode(), address)
        print('sent {} bytes back to {}'.format(sent, address))

    else:
        print('no data from', address)
        break

# ソケットをクローズする
print('closing socket')
sock.close()


