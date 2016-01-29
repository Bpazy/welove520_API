# API分析

##我们的家
```
"access_token": "56294XXXX343086-27152XXXXXa5746dd"(抓包获取，登录之后保持不变)
"task_type": "7", (不同动作有不同的值)
"love_space_id": "844424932415867",(每一个家庭有唯一的值) 
"sig": "WXjA+ujXTVKUfv9lfVMGo6pxbis=" (抓包获取，每个动作有不同的值，重新登录会变更因此需要重新抓包)
```

##详细API
```
Host: api.welove520.com
/v1/game/house/info  房间物品的详细信息
/v1/game/house/tips  自己的信息
/v1/game/house/info  点击随机的时候，返回JSON，love_space_id为房间编码
/v1/game/house/task  执行各种动作
/v1/geo/location     设置位置
/v1/geo/weathers     获取双方天气，基于location发送的数据获取

app_key: ac5f3XXXXa4344c4
love_space_id  房间唯一编号
access_token: 56294XXXX343086-27152XXXXXa5746dd  固定值
sig: 
{    
    洗澡: “Jz8sL8eV2znw0p9abpgyJjGhgPI=”,
    吃饭: “lU/v4QdYZe8b3IV5R0+fPNreBu0=”,
    休息: “WXjA+ujXTVKUfv9lfVMGo6pxbis=”,
    睡觉: “3bKsFw8msyDuXiHJSPVsTH5CSnc=”,
    互动: “5EAfe5e0ZVx4V1vrZLySGe6/Mo0=”
}
task_type: 
{
    休息: 7,
    洗澡: 6,
    吃饭: 4,
    睡觉: 5,
    互动: 13
}
```
<br>
## Demo(洗澡)[python example](https://github.com/Bpazy/welove520_API/blob/master/welove.py):

Request:
```
POST /v1/game/house/task HTTP/1.1
Content-Type: application/x-www-form-urlencoded
Welove-UA: [Device:Nexus 5][OSV:6.0.1][CV:Android2.8.3][WWAN:0][zh_CN][platform:wandoujia][WSP:2]
Accept-Language: zh_CN
User-Agent: Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5 Build/MMB29T)
Host: api.welove520.com
Connection: Keep-Alive
Accept-Encoding: gzip
Content-Length: 123

access_token=56294XXXX343086-27152XXXXXa5746dd&love_space_id=844424932415867&task_type=6&sig=Jz8sL8eV2znw0p9abpgyJjGhgPI%3D
```

Reponse:
```
HTTP/1.1 200 OK
Server: UGuess
Date: Sat, 23 Jan 2016 03:13:03 GMT
Content-Type: application/json;charset=UTF-8
Content-Length: 215
Connection: keep-alive
Cache-Control: no-cache

{"result":1,"messages":[{"lasting_days":0,"count":1,"msg_type":37,"task_type":6,"remain_time":21600},{"msg_type":31,"house":{"love_space_id":"844424932415867","recycle_card":1,"diamond":142736,"house_id":2283899}}]}
```
