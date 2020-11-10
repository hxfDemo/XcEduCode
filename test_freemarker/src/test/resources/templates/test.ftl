<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!<br/>
学生的个数：${stus?size}
<table >
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>出生日期</td>
    </tr>

    <#if stus??>

    <#list stus as  stu>
    <tr>
        <td>${stu_index+1}</td>
        <td <#if stu.name=='小明'> style="background: #3fff1a;"</#if>>${stu.name}</td>
        <td>${stu.age}</td>
        <#--<td <#if stu.money gt 300>style="background: #6bf3ff;"</#if>>${stu.money}</td>-->

        <td <#if (stu.money > 300)>style="background: #6bf3ff;"</#if>>${stu.money}</td>
        <td>${stu.birthday?date}</td>
        <td>${stu.birthday?date}</td><br>
        显示年月日: ${stu.birthday?date}<br>
        显示时分秒：${stu.birthday?time}<br>
        显示日期+时间：${stu.birthday?datetime} <br>
        自定义格式化： ${stu.birthday?string("yyyy年MM月")}<br>
    </tr>
    </#list>
    </#if>
</table>
<br/>
<#--//使用map[key].变量 获取变量值-->
<#--<#if  stuMap.stu?? && stuMap?? >-->
姓名：${(stuMap['stu1'].name)!''}<br/>
年龄：${(stuMap['stu1'].age)!''}<br/>
<#--//使用map.key.变量 获取变量值-->
姓名：${(stuMap.stu1.name)!''}<br/>
年龄：${(stuMap.stu1.age)!''}<br/>

<#--</#if>-->
</body>
<#--//获取map中  的key,使用list遍历-->
<br/>

<#list  stuMap?keys as k>

<tr>
    <td>${k_index+1}</td>
    <td>${stuMap[k].name}</td>
    <td>${stuMap[k].age}</td>
    <td>${stuMap[k].money}</td><br/>
</tr>
</#list>
去点:${point?c}
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
开户行：${data.bank} 账号：${data.account}
</html>