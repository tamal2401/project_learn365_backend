package org.learn365.course.cache;

import org.learn365.modal.course.entity.Chapter;
import org.learn365.modal.course.entity.ChapterVideo;
import org.learn365.modal.course.entity.Standard;
import org.learn365.modal.course.entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class CourseDatastructure
{
    private Node<Standard> rootNode;

    {

    }

    public Node<Standard> cretaRootNode(Standard standard, Long id)
    {
        rootNode = new Node<>(standard, id);
        return rootNode;
    }

    public Node<Subject> createSubjectNode(Subject subject, Long id)
    {
        Node<Subject> subjectchild = new Node<>(subject, id);
        return subjectchild;
    }

    public static List<Subject> getSubjectFromRootNode(Node rootNode)
    {
        List<Node> subjectNode = rootNode.getChildNode();
        List<Subject> subjectlist = subjectNode.stream()
                .map(snode -> (Subject) snode.getElement())
                .collect(Collectors.toList());
        return subjectlist;
    }

    public static Standard getGradeFromRootNode(Node rootNode)
    {
        return (Standard) rootNode.getElement();
    }

}
