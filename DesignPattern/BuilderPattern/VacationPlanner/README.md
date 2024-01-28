# ユーザーからバケーションプランの要望を受け取り、バケーション請求書を作成する

 ## 請求書(VacationInvoice)
 * 詳細情報
    * starting date/ endingdate
    * total amount of people
    * hotel days
    * hotel type
    * FairyWorld days
    * adult fast pass
    * kids fast pass
    * arcade pass
    * VIP tour 1 / VIP tour 2 / VIP tour 3
    * breakfast / lunch / dinner regular restaurants
    * breaskfast / lunch / dinner fine dining restaurants
    * photoshoot
 * 全アイテムの合計金額
 ## 仕様
 * ビルダーパターンを採用する
 * VacationInvoiceBuilderを使って、事前に計画されたバケーションパッケージを提供する
 * VacationDirectorを作成し、ビルダーメソッドを使ってバケーションプランを準備する
 * バケーションの詳細と合計金額を出力する
 ## 提供予定のバケーションオプション
 * couple weekend retreat
    * 2日間、２名用、標準ホテル、通常チケット、２名分の食事パス、花火のVIPチケット
 * family weekend retreat VIP
    * 7日間、６名用、高級ホテル、６名分の食事パス、子供用ファーストパス、写真撮影パス
 * family weekend deluxe
    * 7日間、６名用、高級ホテル、６名用の食事パス、高級レストランの予約、ファミリーファーストパス、VIPツアー、写真撮影パス
