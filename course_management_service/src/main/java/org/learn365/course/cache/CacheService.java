package org.learn365.course.cache;

import org.learn365.course.repository.StandardRepository;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.course.entity.Chapter;
import org.learn365.modal.course.entity.ChapterVideo;
import org.learn365.modal.course.entity.Standard;
import org.learn365.modal.course.entity.Subject;
import org.learn365.modal.test.entity.GradesEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CacheService
{
    private static final Map<String, Node> GRADE_NODE_MAP = new ConcurrentHashMap<>();
    private static CacheService cacheService = null;


    @Autowired
    private StandardRepository standardRepository;
    private CourseDatastructure courseDatastructure;

    private CacheService(CourseDatastructure courseDatastructure)
    {
        this.courseDatastructure = courseDatastructure;
    }

    public static CacheService getCacheInstance(
            CourseDatastructure courseDatastructure)
    {
        if (cacheService == null)
        {
            synchronized (CacheService.class)
            {
                if (cacheService == null)
                {
                    cacheService = new CacheService(courseDatastructure);
                }

            }
        }
        return cacheService;
    }

    public List<String> gradeList()
    {
        return standardRepository.findAllGrade();
    }


    public void prepareNodeTree(String gradename)
    {
        Optional<Standard> optionalStandard = standardRepository.findByName(
                gradename);
        Standard standard = optionalStandard.orElseThrow(
                () -> new CoursePortFolioNotFoundException(
                        "Error in loading the course in cache"));
        Standard cacheStandardCopy = new Standard();
        BeanUtils.copyProperties(standard, cacheStandardCopy);
        cacheStandardCopy.setSubjects(null);
        Node<Standard> rootNode = courseDatastructure.cretaRootNode(standard,
                standard.getId());
        GRADE_NODE_MAP.put(gradename, rootNode);
        createSubjectNode(standard.getSubjects(), rootNode);

    }

    private Node createSubjectNode(List<Subject> subjects, Node rootNode)
    {
        List<Node> subjectChildNode = subjects.stream().map(subject ->
        {
            Subject cacheSubjectCopy = new Subject();

            BeanUtils.copyProperties(subject, cacheSubjectCopy);
            Node<Subject> nodeSubject = courseDatastructure.createSubjectNode(
                    cacheSubjectCopy,
                    cacheSubjectCopy.getId());
            return nodeSubject;
        }).collect(Collectors.toList());
        rootNode.setChildNode(subjectChildNode);

        return rootNode;
    }

    public void cachePut(String key, Node rootNode)
    {
        GRADE_NODE_MAP.put(key, rootNode);
    }

    public Node getValue(String key)
    {
        return GRADE_NODE_MAP.get(key);
    }

}
