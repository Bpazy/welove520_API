# coding: UTF-8
import time

import requests


def get_current_time():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(time.time()))


if __name__ == "__main__":
    access_token = "56294XXXX343086-27152XXXXXa5746dd"
    # 休息
    data = {"access_token": access_token,
            "task_type": "7",
            "love_space_id": "844424932415867",
            "sig": "WXjA+ujXTVKUfv9lfVMGo6pxbis="}
    r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
    print(get_current_time() + " 休息 " + r.text)

    # 洗澡
    data = {"access_token": access_token,
            "task_type": "6",
            "love_space_id": "844424932415867",
            "sig": "Jz8sL8eV2znw0p9abpgyJjGhgPI="}
    r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
    print(get_current_time() + " 洗澡 " + r.text)

    # 吃饭
    data = {"access_token": access_token,
            "task_type": "4",
            "love_space_id": "844424932415867",
            "sig": "lU/v4QdYZe8b3IV5R0+fPNreBu0="}
    r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
    print(get_current_time() + " 吃饭 " + r.text)

    # 睡觉
    data = {"access_token": access_token,
            "task_type": "5",
            "love_space_id": "844424932415867",
            "sig": "3bKsFw8msyDuXiHJSPVsTH5CSnc="}
    r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
    print(get_current_time() + " 睡觉 " + r.text)

    # 互动
    data = {"access_token": access_token,
            "task_type": "13",
            "love_space_id": "844424932415867",
            "sig": "5EAfe5e0ZVx4V1vrZLySGe6/Mo0="}
    r = requests.post("http://api.welove520.com/v1/game/house/task", data=data)
    print(get_current_time() + " 互动 " + r.text)
