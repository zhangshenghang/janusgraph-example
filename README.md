### janusgraph java调用
#### 学习使用

#### 结构
> 1.代码中包含传播路径查询demo  
> >  查询入口 PropagationQueryTest.java   
> >  数据插入在：PropagationQueryTest.addTestData() 

#### 可视化插件安装 - GraphEXP ：https://datamining.blog.csdn.net/article/details/103896472
#### 可视化插件安装 - Gephi ：https://datamining.blog.csdn.net/article/details/103894994
#### JanusGraph单机版安装 ： https://datamining.blog.csdn.net/article/details/103894355


```
在配置文件conf/janusgraph-hbase-solr.properties中添加HBase的访问，配置参数如下：

storage.backend=hbase //使用HBase作为存储后端
storage.hostname=zookeeper-host1,zookeeper-host2,zookeeper-host3 //HBase的Zookeeper
storage.hbase.table=janusgraph//HBase存储JanusGraph元数据的表名
storage.hbase.ext.zookeeper.znode.parent=/hbase //HBase使用的Znode
storage.hbase.ext.hbase.zookeeper.property.clientPort=2181 //端口
HBase的查询是基于Rowkey，所以在条件查询这块是弱项，一旦Rowkey确定如果有新的查询就比较难。所以为了解决这个问题，JanusGraph引入了索引后端，下面我们介绍下如何配置Solr作为JanusGraph的索引后端。配置文件conf/janusgraph-hbase-solr.properties配置参数如下：

index.search.backend=solr //使用solr作为索引后端
index.search.solr.mode=cloud //solr使用集群模式
index.search.solr.zookeeper-url=zookeeper-host1:2181/solr,zookeeper-
host2:2181/solr,zookeeper-host3:2181/solr //HBase使用的Zookeeper
index.search.solr.configset=janusgraph //Solr使用HBase的元数据表名
```
