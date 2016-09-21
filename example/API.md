# API分析

### 1. URL
```
Host: api.welove520.com
/v1/game/house/info  房间物品的详细信息
/v1/game/house/tips  自己的信息
/v1/game/house/info  点击随机的时候，返回JSON，love_space_id为房间编码
/v1/game/house/task  执行各种动作
/v1/geo/location     设置位置
/v1/geo/weathers     获取双方天气，基于location发送的数据获取
```

`access_token`: `56294XXXX343086-27152XXXXXa5746dd`(抓包获取，登录之后保持不变)

`sig`: `WXjA+ujXTVKUfv9lfVMGo6pxbis=` (**加密字段**)

**加密方式**:`{GET|POST}&{url}&{content}`并且`url`和`content`都是经过`Base64`编码，
`POST`、`url` 都是固定的，`content`则是请求的信息出除去`sig`字段，对这一构造得到的字符串进行`HmacSHA1`加密之后再进行`Base64`编码就是请求中需要的`sig`了[查看详细](http://blog.csdn.net/hanziyuan08/article/details/52606908)

### 2. 我们的家
```
"task_type": "7", (不同动作有不同的值)
"love_space_id": "844424932415867",(每一个家庭有唯一的值) 

app_key: ac5f3XXXXa4344c4
love_space_id  房间唯一编号
access_token: 56294XXXX343086-27152XXXXXa5746dd  固定值
sig: Jz8sL8eV2znw0p9abpgyJjGhgPI=
task_type: 
{
    休息: 7,
    洗澡: 6,
    吃饭: 4,
    睡觉: 5,
    互动: 11
}


随机拜访：
    http://api.welove520.com/v1/game/house/info
参数：
    access_token=56294996134XXXXXXXX99a09974dd0&love_space_id=random&sig=EqX50TBS1oaCkDKmmA7Y2Jp9SKI=
返回值：
    {
        "result": 1,
        "messages": [
            {
                "view_ad_count": 0,
                "users": [
                    {
                        "gender": 1,
                        "user_id": "562949973509257",
                        "gender_update": 1
                    },
                    {
                        "gender": 0,
                        "user_id": "562949973509232",
                        "gender_update": 1
                    }
                ],
                "faces": [
                    {
                        "face": [
                            {
                                "color": 0,
                                "status": 1,
                                "goods_id": 50600001
                            },
                            ...
                        ],
                        "user_id": "562949973509257"
                    },
                    ...
                ],
                "pay_old_goods": 0,
                "cur_house_num": 0,
                "medals": [
                    {
                        "level": 1,
                        "owner_id": "562949973509257",
                        "medal_id": 1
                    },
                    ...
                ],
                "msg_type": 30,
                "fashions": [
                    {
                        "fashion": [
                            {
                                "id": 459319,
                                "count": 1,
                                "goods_id": 20300031,
                                "wear": 1
                            },
                            ...
                        ],
                        "user_id": "562949973509257"
                    },
                    ...
                ],
                "house": {
                    "estate_count": 1,
                    "is_stroke_pet": 0,
                    "headurl": "",
                    "love_space_id": "8444xxxxxxxxx2707",
                    "name": "",
                    "like_today": 1,
                    "house_id": 6690739,
                    "photo_id": "0",
                    "cover_id": 0
                },
                "decorations": [
                    {
                        "id": 2285258,
                        "decorate": 1,
                        "status": 1,
                        "rotation": 90,
                        "goods_id": 11900107,
                        "z": 0,
                        "house_num": 0,
                        "y": 6,
                        "parent_id": 0,
                        "x": 15
                    },
                    ...
                ],
                "game_version": 0
            }
        ]
    }
称赞:
    http://api.welove520.com/v1/game/house/task
参数:
    task_type=8&house_num=0&access_token=56294996134XXXXXXXX99a09974dd0&love_space_id=8444xxxxxxxxx2707&sig=Ca8fn0iQ7attLlZjZBYUdY9IXPQ=
返回值:
    {
        "result": 1,
        "messages": [
            {
                "lasting_days": 0,
                "is_home_done": 1,
                "count": 1,
                "msg_type": 37,
                "task_type": 8,
                "remain_time": 0
            },
            {
                "msg_type": 31,
                "house": {
                    "love_space_id": "844424932415867",
                    "recycle_card": 0,
                    "diamond": 13371,
                    "house_id": 2283899
                }
            }
        ]
    }
```
### 3. 爱情树
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
        ...
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
        ...
    ],
    "low_price_total": 1,
    "records": [
        {
            "lasting_days": 915,
            "invalid": 0,
            "complete": 1,
            "date": "2016-09-15"
        },
        ...
    ],
    "lover_lasting_days": 274,
    "tree_id": 844424932415867
}
```
