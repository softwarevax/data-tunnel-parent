#### 1、版本依赖统一放入tunnel-dependencies-bom, tunnel-dependencies-bom模块需先install方可被使用
#### 2、source -> channel -> sink
##### 2.1 source ->memory -> sink
```$xslt
AbstractTunnelTask task = TunnelTaskFactory.create(ctx, HttpSource.class, MemoryChannel.class, TcpSink.class);
Thread thread = new Thread(task);
thread.start();
```
##### 2.2 source -> RocketMQ -> sink
```$xslt
String topic = "test";
Context ctx = new Context();
ctx.put(Constants.MQ_PRODUCER_GROUP_NAME, "ProducerGroupName");
ctx.put(Constants.MQ_SERVER_SRV_ADDRESS, "localhost:9876");
ctx.put(Constants.MQ_PRODUCER_INSTANCE_NAME, "Producer");
ctx.put(Constants.MQ_MSG_TIMEOUT, "3000");
ctx.put(Constants.MQ_MSG_SEND_FAILED_TRY_TIME, "2");
ctx.put(Constants.MQ_TOPIC, topic);

ctx.put(Constants.MQ_CONSUMER_GROUP_NAME, "ConsumerGroupName");
ctx.put(Constants.MQ_CONSUMER_INSTANCE_NAME, "Consumer");

AbstractTunnelTask task = TunnelTaskFactory.create(ctx, HttpSource.class, AlibabaRocketMQChannel.class, TcpSink.class);
Thread thread = new Thread(task);
thread.start();
```

#### 3、运行
```$xslt
说明: 
1、common、source、channel、sink将依赖包打进插件中，test(使用source、channel、sink插件的引用)不将依赖包打进，避免引用包版本冲突
2、待运行jar包的绝对路径和lib目录的绝对路径，如果待运行的jar包在lib内，只是用lib目录即可，多个目录使用分号分隔，linux系统用冒号分隔
java -classpath E:\project\idea\git\data-tunnel-parent\bin\tunnel-test-1.0.0.jar;E:\project\idea\git\data-tunnel-parent\bin\lib\* org.platform.tunnel.test.App
```
##### 3.1、修改根目录pom文件中的属性bin.path的值，改成jar包输出的绝对路径
##### 3.2、install打包安装，应用jar包移至bin目录下，并删除模块target(maven-antrun-plugin)
##### 3.3、运行bin目录下的start.bat文件即可
