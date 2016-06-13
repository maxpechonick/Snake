package Snake.Statistic

import Snake.Entity.Notation

/**
 * Analysis of game statistics
 */
class ScalaStatistic {
  /**
   * Find count of every figure
   */
  def getStatistic(gameInfo: Array[Notation]){
    val tempArray = for (temp: Notation <- gameInfo) yield temp
    val statistic = new Array[Int](4)

    for (temp: Notation <- tempArray)
      statistic(temp.getDirection-1)+=1

    new StatisticTable("Statistic", statistic, statistic.indexOf(statistic.max))
  }
}
