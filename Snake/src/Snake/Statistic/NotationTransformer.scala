package Snake.Statistic

import Snake.Entity.Notation


class NotationTransformer {
  def parse(temp: Any): Any = {
    temp match {
      case mas: Notation => getOutputMessage(mas)
      case moves: Int => "Game is end. Number of steps a bot is " + Integer.toString(moves)
    }
  }

  def getOutputMessage(temp: Notation): String = {
    "Head cords: " + temp.getHeadX+"."+temp.getHeadY+"\n"+
    "Apple cords: " + temp.getAppleX+"."+temp.getAppleY+"\n"+
    "Direction: " + Notation.getDirectionName(temp.getDirection)
  }
}
