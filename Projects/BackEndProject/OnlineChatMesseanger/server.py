"""
このファイルはサーバーのメインスクリプトです。
ソケットを作成し、クライアントからの接続を待ちます。
接続が確立されると、クライアントとの通信を処理します。
"""

import socket
import os
from pathlib import Path


