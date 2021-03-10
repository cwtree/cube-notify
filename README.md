# cube-notify
消息通知服务

# 邮件示例请求报文（密送？）
```
{
  "content": "邮件内容",
  "subject": "邮件主题",
  "toList": [
    "chiweitree2008@126.com"
  ]
}
```

```
{
  "subject": "中国移动CMCC",
  "templateDto": {
    "bizDesc": "消息通知系统发生异常，异常信息为【服务器异常信息描述】",
    "bizName": "消息通知系统"
  },
  "toList": [
    "chiweitree2008@126.com",
	"cwtree@mail.ustc.edu.cn"
  ]
}
```

# 短信（待实现）
