# -
功能实现不多，代码丑
网络五子棋（只实现了部分功能）



这里只是记录写了的辣鸡代码，完全不能当作一个完整的程序，就当作给以后自己看看现在有多憨。



因为课设时间紧迫而且不会GUI、hadoop、没了解多线程、网络编程，写代码时间只有一天多点时间（就睡了3小时qwq），所以很多功能没有实现。

本来想着复盘、悔棋、导出棋盘，数据库连hbase什么的，还记录了棋谱虽然后来没用到，大概写了一点数据库功能但也没用。



服务端开着server包下Gobang_server类就好

客户端运行client包下Interface类

服务器配置信息在database包下ServerInfo.java，但是敲到后面时间紧迫很多都用混了，前后端不能分离。

棋盘配置信息在client包下的Config.java

很多地方没有注释，太困了又急当时能记住干啥的就不注释解释了，估计过段时间就看不懂了。



客户端需要运行两个不同名字的客户端才能够正常使用对战功能，匹配已经自带了，能多棋盘对战（反正是多线程。。



有个致命的bug，先一口气写完逻辑在跑的代码，写到后面发现，连续发送两条消息会存在后发先至的情况，导致严重bug。由于是在本机，没时间整我就发送消息sleep了下。（特别憨憨）想想应该有什么东西能解决，或者来回发送消息验证（感觉也特别蠢）
