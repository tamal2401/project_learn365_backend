package org.learn365.course.cache;

import java.util.List;

public class Node<E>
{
    private Long id;
    private E e;
    private List<Node> childnode;

    public Node(E e, Long id)
    {
        this.e = e;
        this.id = id;
    }

    public List<Node> getChildNode()
    {
        return childnode;
    }

    public void setChildNode(List<Node> childnode)
    {
        this.childnode = childnode;
    }

    public E getElement()
    {
        return this.e;
    }
}
