package customWarts

import org.wartremover.{ WartTraverser, WartUniverse }
import scala.quoted.Expr

object Unimplemented extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    new u.Traverser(this) {
      import q.reflect.*

      object PrimitivePlusChar {
        def unapply[A](t: Expr[A]): Boolean = t match {
          case '{ ($x1: Char) + ($x2: Char) } => true
          case '{ ($x1: Byte) + ($x2: Char) } => true
          case '{ ($x1: Short) + ($x2: Char) } => true
          case '{ ($x1: Int) + ($x2: Char) } => true
          case '{ ($x1: Long) + ($x2: Char) } => true
          case '{ ($x1: Float) + ($x2: Char) } => true
          case '{ ($x1: Double) + ($x2: Char) } => true
          case _ => false
        }
      }

      object CharPlusPrimitive {
        def unapply[A](t: Expr[A]): Boolean = t match {
          case '{ ($x1: Char) + ($x2: Byte) } => true
          case '{ ($x1: Char) + ($x2: Short) } => true
          case '{ ($x1: Char) + ($x2: Int) } => true
          case '{ ($x1: Char) + ($x2: Long) } => true
          case '{ ($x1: Char) + ($x2: Float) } => true
          case '{ ($x1: Char) + ($x2: Double) } => true
          case _ => false
        }
      }

      override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
        val error_message = "Implicit char conversion"
        tree match {
          case t if hasWartAnnotation(tree) =>
          case t if t.isExpr =>
            tree.asExpr match {
              case PrimitivePlusChar() =>
                error(tree.pos, error_message)
              case CharPlusPrimitive() =>
                error(tree.pos, error_message)
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
