# mysql的log与事务实现
---

## 事务的ACID
- **原子性（Atomicity）**  
- **一致性（Consistency）**
- **隔离性（Isolation）**
- **持久性（Durability）**

## Undo Log（实现原子性，回滚）
在操作任何数据之前，**==将数据备份到一个地方(Undo Log）==**，再进行数据的修改。  
如果出现了错误或者用户执行了ROLLBACK语句，系统可以利用Undo Log中的备份将数据恢复到事务开始之前的状态。  
所以也实现了**回滚**功能

### 用于MVCC（多版本并发控制）
#### 多版本控制的作用


#### 多版本控制的实现
在底层中，所有的数据结构都会额外加上：
- 数据事务的创建id
- 事务的删除id，也有可能是未定义

例如表格：
```sql
CREATE TABLE student {
  id INT (4) PRIMARY KEY,
  name VARCHAR (20) ,
  age INT (4)
} DEFAULT CHARSET=utf8;

INSERT INTO student (id, name, age) VALUES (1, 'li', 20);
```
上面的li在mvcc中存储的格式为

|id|name|age|创建事务id|删除事务id|
|----|-----|---|----|----|
| 1 | li | 20 | 1 | nodefined |

操作：  
    - **select**: select * from student where id=1;  
    - **update**: update student set age=21 where id=1;
    
##### mvcc的工作原理：  
- 





## Redo Log（实现持久性）
在修改数据后，**==将最新修改的数据存储到一个地方(Redo Log）==**，再进行数据的修改，**主要用于在Mysql崩溃后，恢复到崩溃前**。



## Undo + Redo记录事务的过程
假如有A, B两个数据，值分别为1，2，开始一个事务，事务的操作为内容为：把1修改成3，2修改成4，记录如下：
1. 开始事务
2. 记录A=1到**Undo日志**
3. 修改内存中A的值为3
4. 记录A=3到**Redo日志**
5. 记录B=2到**Undo日志**
6. 修改内存中B的值为4
7. 记录B=4到**Redo日志**
8. 事务提交：--**将Redo日志写入磁盘**--，记录到binlog

### 过程分析
- 2,4,5,7都是先将日志写到对应Undo和Redo日志的**内存Buffer缓冲区**，当需要时候再刷新到磁盘
- 1

## BinLog (与实现mysql事务无关)
Binlog只是用于mysql的主从同步