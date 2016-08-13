import time

import requests
import toml


def get_current_time():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(time.time()))


# 从welove.toml中读取信息配置信息并且发送Post请求
if __name__ == '__main__':
    confFile = open("welove.toml", encoding="utf-8")
    config = toml.loads(confFile.read())
    for key in config:
        data = {"access_token": config[key]["access_token"],
                "task_type": config[key]["task_type"],
                "love_space_id": config[key]["love_space_id"],
                "sig": config[key]["sig"]}
        r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
        print("%s %s %s" % (get_current_time(), key, r.text))
