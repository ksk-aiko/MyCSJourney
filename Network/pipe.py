import os
# パイプを作成 
r, w = os.pipe()
# 子プロセスを生成
pid = os.fork()

# 子プロセス
if pid > 0:
    # 親プロセスでは書き込みを行うため、読み込み用のファイルディスクリプタを閉じる
    os.close(r)
    # 親プロセスからのメッセージを生成
    message = "Message from parent with pid {}".format(os.getpid())
    # 生成したメッセージを表示
    print("Parent, sending out the message - {}".format(message, os.getpid()))
    # メッセージをパイプに書き込む
    os.write(w, message.encode('utf-8'))
else:
    # 子プロセスでは読み込みを行うため、書き込み用のファイルディスクリプタを閉じる
    os.close(w)
    print("Fork is 0, this is a Child PID:", os.getpid())
    # 読み取り用のファイルディスクリプタを開く
    r = os.fdopen(r)
    # パイプからメッセージを読みとり、表示
    print("Incoming string: ", r.read())
