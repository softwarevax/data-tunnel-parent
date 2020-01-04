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