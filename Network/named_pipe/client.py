import os
import json

# config.jsonを開いてロードする
config = json.load(open('config.json'))

# パイプを開く
f = open(config['filepath'], 'r')

# パイプが存在する間、ファイルからデータを読み込み、それが空でない場合は表示する
flag = True
while flag:
    if not os.path.exists(config['filepath']):
        flag = False

    data = f.read()
    if len(data) != 0:
        print("Data received from pipe: '{}'".format(data))

# パイプを閉じる
f.close()