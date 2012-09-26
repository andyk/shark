package shark.execution

import org.apache.hadoop.hive.ql.exec.{LimitOperator => HiveLimitOperator}
import scala.collection.Iterator
import scala.reflect.BeanProperty
import spark.Split

class LimitOperator extends UnaryOperator[HiveLimitOperator] {

  @BeanProperty var limit: Int = _

  override def initializeOnMaster() {
    limit = hiveOp.getConf().getLimit()
  }

  override def processPartition(split: Split, iter: Iterator[_]) = iter.take(limit)
}

