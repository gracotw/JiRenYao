# JiRenYao 簡訊

此為 Android app. 用以掃描 實聯制 的 QR code.
並且保護使用者的隱私.

本 app 模擬簡訊界面. 但並不會真的送出簡訊.
所有的簡訊都存在本機. 等使用者真正需要把資料交給別人時,
從手機內部取出就可以. 資料存成 json format.

模擬一個簡訊界面完全只是為了給店員做人工檢查用.

我們相信, 只有把資料存在本端, 程式碼開源, 自己決定要不要公佈本端資料, 
才是真正的保護隱私.

2022/04/27: 中華民國國家衛生指揮中心中央流行疫情指揮中心宣佈取消實聯制措施, 可以不用再安裝這個 app 了. 已安裝者也可以移除了.

This is an Android app that mimics the GUI of sending SMS. It is
mainly for dealing with the privacy issues on Taiwan's QR code for 
real-name registration. This app will keep those 1922 messages locally
instead of sending the real SMS out.

27th Apr 2022: The Central Epidemic Command Center of Taiwan (ROC) announced that there's no more real-name registration for public services and places. Please stop installing this app as it is useless anymore.

## 使用方式

點開 "l922". 按下 "送出", 然後掃描 QR code. 然後再按下 "送出".

界面內只會顯示最近兩筆紀錄.

完整紀錄檔案存在 'Android/data/onion.d25a470b.gracotw.jirenyao/files/MyFileStorage/l922.json'

## 如何下載?

請點選 Release, 然後下載 apk 至手機安裝. 
