package EG4;

/**
*
* Copyright (c) 1996-1997 Sun Microsystems, Inc.
*
* Use of this file and the system it is part of is constrained by the
* file COPYRIGHT in the root directory of this system.
*
*/

/* This is an example of how the Visitor pattern might be used to
  implement the dumping code that comes with SimpleNode.  It's a bit
  long-winded, but it does illustrate a couple of the main points.

  1) the visitor can maintain state between the nodes that it visits
  (for example the current indentation level).

  2) if you don't implement a jjtAccept() method for a subclass of
  SimpleNode, then SimpleNode's acceptor will get called.  This almost
  always indicates an error, as explained below un "SimpleNode and
  Extended AST Nodes"

  3) the utility method childrenAccept() can be useful when
  implementing preorder or postorder tree walks.

*/

public class Eg4DumpVisitor implements Eg4Visitor
{
 private int indent = 0;

 // Visitors can include any number of helper fields and methods, like
 // the indent variable above and the following method
 // indentString(), which is used during the dumping

 private String indentString() {
   StringBuffer sb = new StringBuffer();
   for (int i = 0; i < indent; ++i) {
     sb.append(" ");
   }
   return sb.toString();
 }


 // The real "meat" of the eg4DumpVisitor starts here.

 // This Visitor contains a visit() method (below) for each specific
 // type of AST node in the grammar--it also contains a catch-all method
 // for SimpleNode, which is inherited by all the specific
 // AST nodes--if this SimpleNode method gets used, a kind
 // of error message is printed

 // when an AST node "accepts" a visitor, its jjtAccept() method
 // makes a callback to
 // the visit() method of the Visitor itself, passing a reference to
 // itself; the particular visit() method that gets performed will
 // be the one whose first argument matches the type of the calling
 // AST node

 // in Java parlance, the visit() method of the Visitor is
 // overloaded, and the various flavors of visit() are
 // distinguished by the type of the first argument, which is
 // some subtype of AST node

 public Object visit(SimpleNode node, Object data) {
   System.out.println(indentString() + node +
		       ": acceptor not unimplemented in subclass?");

                      // I think UNimplemented above is a typo
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }

 // here are the visit() methods for specific AST node classes

 public Object visit(ASTStart node, Object data) {
   System.out.println(indentString() + node);
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }

 public Object visit(ASTAdd node, Object data) {
   System.out.println(indentString() + node);
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }

 public Object visit(ASTMult node, Object data) {
   System.out.println(indentString() + node);
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }

 public Object visit(ASTMyOtherID node, Object data) {
   System.out.println(indentString() + node);
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }

 public Object visit(ASTInteger node, Object data) {
   System.out.println(indentString() + node);
   ++indent;
   data = node.childrenAccept(this, data);
   --indent;
   return data;
 }
}

/*end*/