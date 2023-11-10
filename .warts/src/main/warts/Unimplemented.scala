package customWarts

import org.wartremover.{ WartTraverser, WartUniverse }
import scala.quoted.Expr

object Unimplemented extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    new u.Traverser(this) {
      import q.reflect.*

      object PrimitivePlusChar {
        def unapply[A](t: Expr[A]): Boolean = t match {
          case '{ ($x1: Byte) + ($x2: Char) } => true
          case '{ ($x1: Short) + ($x2: Char) } => true
          case '{ ($x1: Int) + ($x2: Char) } => true
          case '{ ($x1: Long) + ($x2: Char) } => true
          case '{ ($x1: Float) + ($x2: Char) } => true
          case '{ ($x1: Double) + ($x2: Char) } => true
          case _ => false
        }
      }

      override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
        tree match {
          case t if hasWartAnnotation(tree) =>
          case t if t.isExpr =>
            tree.asExpr match {
              case PrimitivePlusChar() =>
                error(tree.pos, "Custom lint rule triggered")
              case '{ ($x1: String) + ($x2: String) } =>
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
