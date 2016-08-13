
### 下载并使用
```
git clone https://github.com/Bpazy/welove520_API
cd welove520_API
gradlew run
```
微爱请求关键信息在执行后保存在根目录welove.conf中

由Python发送post请求 [Example](https://github.com/Bpazy/welove520_API/blob/master/example/post.py)

添加定时任务每`30`分钟执行一次

`echo "*/30 * * * * python welove.py" >> /var/spool/cron/root`

[微爱API接口](https://github.com/Bpazy/welove520_API/blob/master/example/API.md)
