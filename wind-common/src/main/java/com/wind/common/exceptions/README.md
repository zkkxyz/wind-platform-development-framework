# com.zoe.common.exceptions规范：

[异常码规范]

BaseException：顶级异常无异常码
BizException：异常码以10开头
DaoException：异常码以20开头
CheckedException：异常码以30开头
ParamException：异常码以40开头
IOException：异常码以50开头
NetException：异常码以60开头

基础异常码的组成：10(父级异常)+001(自定义自增异常码)

微服务异常码的组成：10(父级异常)+001(微服务码)+001(自定义自增异常码)





