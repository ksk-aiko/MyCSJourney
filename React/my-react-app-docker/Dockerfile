# ベースイメージとして Node.js を使用
FROM node:14

# アプリケーションディレクトリを作成
WORKDIR /usr/src/app

# package.json と package-lock.json をコピー
COPY package*.json ./

# 依存関係をインストール
RUN npm install

# ソースコードをコピー
COPY . .

# アプリケーションを起動
CMD ["npm", "start"]

