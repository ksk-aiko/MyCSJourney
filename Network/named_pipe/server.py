import os
import json

# config.jsonを開いてロードする
config = json.load(open('config.json'))

# 同じ名前のパイプが存在する場合は削除する
if os.path.exists(config['filepath']):
    os.remove(config['filepath'])

# パーミッション0o600で、パスを指定して名前付きパイプを作成する
os.mkfifo(config['filepath'], 0o600)

print("FIFO named '% s' is created successfully." % config['filepath'])
print("Type in what you would like to send to clients.")

# ユーザーからの入力を取得し、それを名前付きパイプに書き込む。'exit'が入力されるまで続ける
flag = True
while flag:
    inputstr = input()

    if(inputstr == 'exit'):
        flag = False
    else:
        with open(config['filepath'], 'w') as f:
            f.write(inputstr)

# パイプを削除する
os.remove(config['filepath'])