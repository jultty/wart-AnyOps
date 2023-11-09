package customWarts

import org.wartremover.{ WartTraverser, WartUniverse }

object Unimplemented extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    new u.Traverser(this) {
      import q.reflect.*
      override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
        tree match {
          case _ if hasWartAnnotation(tree) =>
          case _ if tree.isExpr =>
            tree.asExpr match {
              case '{ ??? } =>
                error(tree.pos, "Custom lint rule triggered")
              case _ =>
                super.traverseTree(tree)(owner)
            }
          case _ =>
            super.traverseTree(tree)(owner)
        }
      }
    }
  }
}
