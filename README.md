## 数据库防篡改

demo采用 SM2 加密算法，对防篡改数据进行备份加密，加密算法是别人的。

防篡改表设计：

t_news_back

| 字段名称     | 类型        | m描述                |
| ------------ | ----------- | -------------------- |
| ID           | varchar(64) | 主键                 |
| TABLENAME    | varchar(50) | 业务表Code           |
| BUSINESSID   | varchar(64) | 业务数据主键         |
| FIELDNAME    | varchar(50) | 业务表防篡改字段Code |
| ENCRYPTVALUE | text        | 加密数据             |
| CREATETIME   | date        | 创建时间             |

防篡改字段在数据库中直接修改后，界面展示：

![1583981188496](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1583981188496.png)
