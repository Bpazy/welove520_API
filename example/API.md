# API分析

1. ###详细API
```
Host: api.welove520.com
/v1/game/house/info  房间物品的详细信息
/v1/game/house/tips  自己的信息
/v1/game/house/info  点击随机的时候，返回JSON，love_space_id为房间编码
/v1/game/house/task  执行各种动作
/v1/geo/location     设置位置
/v1/geo/weathers     获取双方天气，基于location发送的数据获取
```

2. ###我们的家
```
"access_token": "56294XXXX343086-27152XXXXXa5746dd"(抓包获取，登录之后保持不变)
"task_type": "7", (不同动作有不同的值)
"love_space_id": "844424932415867",(每一个家庭有唯一的值) 
"sig": "WXjA+ujXTVKUfv9lfVMGo6pxbis=" (抓包获取，每个动作有不同的值，重新登录会变更因此需要重新抓包)

app_key: ac5f3XXXXa4344c4
love_space_id  房间唯一编号
access_token: 56294XXXX343086-27152XXXXXa5746dd  固定值
sig(每次登陆生成新的值): 
{    
    洗澡: "Jz8sL8eV2znw0p9abpgyJjGhgPI=",
    吃饭: "lU/v4QdYZe8b3IV5R0+fPNreBu0=",
    休息: "WXjA+ujXTVKUfv9lfVMGo6pxbis=",
    睡觉: "3bKsFw8msyDuXiHJSPVsTH5CSnc=",
    互动: "5EAfe5e0ZVx4V1vrZLySGe6/Mo0="
}
task_type: 
{
    休息: 7,
    洗澡: 6,
    吃饭: 4,
    睡觉: 5,
    互动: 11
}
```
3. ###爱情树
```
获取爱情树信息：
http://api.welove520.com/v1/game/tree/getInfo?access_token=562949961313211-2cxxxxx7e299a0997xxx0&app_key=ac5XXXXXa4344c4&screen_type=102&tree_version=30&sig=XLqfKUjNrU0PlpKeFOA4SwroVRs=
返回值：
{
    "result": 1,
    "next_level_growth": 1100,
    "lover_lack_sunlight": 1,
    "new_op_record": 1,
    "stage": 2,
    "next_stage_growth": 40,
    "level": 31,
    "name": "美美的割割",
    "level_growth": 510,
    "card": 0,
    "gold": 160,
    "lack_water": 1,
    "lover_lack_water": 1,
    "lack_sunlight": 1,
    "decoration": [
        {
            "position": 4,
            "goods_id": 101
        },
        {
            "position": 3,
            "goods_id": 102
        },
        {
            "position": 2,
            "goods_id": 115
        },
        {
            "position": 1,
            "goods_id": 116
        },
        {
            "position": 1001,
            "goods_id": 117
        },
        {
            "position": 1000,
            "goods_id": 118
        }
    ],
    "tree_id": 844424932415867,
    "growth": 24635
}

浇水：
http://api.welove520.com/v1/game/tree/op
请求值：
access_token=562949961313211-2cxxxxx7e299a0997xxx0&app_key=ac5XXXXXa4344c4&op=1&sig=2MH+eM+A4deR58Zimzk5Tk1Zbkk=
返回值：
{
    "extra_growth": 0,
    "result": 1,
    "next_level_growth": 1100,
    "stage": 2,
    "next_stage_growth": 35,
    "level": 31,
    "name": "美美的割割",
    "level_growth": 515,
    "card": 0,
    "lover_lasting_days": 915,
    "tree_id": 844424932415867,
    "lasting_days": 915,
    "level_up": 0,
    "complete_today": 0,
    "growth_increase": 5,
    "growth": 24640
}

晒太阳：
http://api.welove520.com/v1/game/tree/op
请求值：
access_token=562949961313211-2cxxxxx7e299a0997xxx0&app_key=ac5XXXXXa4344c4&op=2&sig=MpxK/hUWRSnyDFkpSnOl06d3UiQ=
返回值：
{
    "extra_growth": 0,
    "result": 1,
    "next_level_growth": 1100,
    "stage": 2,
    "next_stage_growth": 30,
    "level": 31,
    "name": "美美的割割",
    "level_growth": 520,
    "card": 0,
    "lover_lasting_days": 915,
    "tree_id": 844424932415867,
    "lasting_days": 915,
    "level_up": 0,
    "complete_today": 1,
    "growth_increase": 5,
    "growth": 24645
}

爱情树记录查询：
http://api.welove520.com/v1/game/tree/records?access_token=562949961313211-2cxxxxx7e299a0997xxx0&app_key=ac5XXXXXa4344c4&sig=1nuLuqYjMhOcU2xlo7pG0Mz1n00=
返回值：
{
    "result": 1,
    "lasting_days": 915,
    "lover_lack_water": 1,
    "cur_month": 9,
    "lover_lack_sunlight": 1,
    "card_balance": 11,
    "low_price_left": 1,
    "lover_records": [
        {
            "lasting_days": 0,
            "invalid": 0,
            "complete": 0,
            "date": "2016-09-15"
        },
        {
            "lasting_days": 274,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-14"
        },
        {
            "lasting_days": 273,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-13"
        },
        {
            "lasting_days": 272,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-12"
        },
        {
            "lasting_days": 271,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-11"
        },
        {
            "lasting_days": 270,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-10"
        },
        {
            "lasting_days": 269,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-09"
        },
        {
            "lasting_days": 268,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-08"
        },
        {
            "lasting_days": 267,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-07"
        }
    ],
    "low_price_total": 1,
    "records": [
        {
            "lasting_days": 915,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-15"
        },
        {
            "lasting_days": 914,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-14"
        },
        {
            "lasting_days": 913,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-13"
        },
        {
            "lasting_days": 912,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-12"
        },
        {
            "lasting_days": 911,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-11"
        },
        {
            "lasting_days": 910,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-10"
        },
        {
            "lasting_days": 909,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-09"
        },
        {
            "lasting_days": 908,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-08"
        },
        {
            "lasting_days": 907,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-07"
        }
    ],
    "lover_lasting_days": 274,
    "tree_id": 844424932415867
}
```