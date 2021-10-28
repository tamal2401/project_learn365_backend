package com.learn365.course;

import com.learn365.model.Chapter;
import com.learn365.model.ChapterVideo;
import com.learn365.model.Grade;
import com.learn365.model.Subject;
import com.learn365.util.ReadExcelData;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.learn365.modal.course.request.StandardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Dataloader {

    Map<Integer, List<Grade>> grademap = new HashMap<>();
    Map<Integer, List<Subject>> subjectmap = new HashMap<>();
    Map<Integer, List<Chapter>> chaptermap = new HashMap<>();
    Map<Integer, List<ChapterVideo>> chapterVideomap = new HashMap<>();
    @Autowired
    private ReadExcelData readExcelData;
    @Autowired
    private PrepareDataRequest prepareDataRequest;
    @Autowired
    private CreateCourse createCourse;



    public void startMapping() {
        try {
            ClassLoader classLoader = Dataloader.class.getClassLoader();
            DataFormatter dataFormatter = new DataFormatter();
            readExcelData = new ReadExcelData();
            File f=new File(classLoader.getResource("courseoffering.xlsx").getFile());
            Workbook workbook = readExcelData.getWorkBookSheet(f, 0);

            List<Grade> gradelist = mapGradeDatafromSheet(workbook.getSheetAt(0));
            grademap = gradelist.stream().collect(Collectors.groupingBy(Grade::getId));
            System.out.println(grademap);

            //Subject

            List<Subject> subjectlist = mapSubjectDatafromSheet(workbook.getSheetAt(1));
            subjectmap = subjectlist.stream().collect(Collectors.groupingBy(Subject::getGradeId));
            System.out.println(subjectmap);

            //chapter
            List<Chapter> chapterlist = mapChapterDatafromSheet(workbook.getSheetAt(2));
            chaptermap = chapterlist.stream().collect(Collectors.groupingBy(Chapter::getSubjectId));
            System.out.println(chaptermap);

            //chapterVideo
            List<ChapterVideo> chapterVideolist = mapChapterVideoDatafromSheet(workbook.getSheetAt(3));
            chapterVideomap = chapterVideolist.stream().collect(Collectors.groupingBy(ChapterVideo::getChapterid));
            System.out.println(chapterVideomap);

            preparedata();
            System.out.println("After--------"+grademap);
            List<StandardRequest> standardreq=prepareDataRequest.createCoursePortfolio(grademap);
            standardreq.forEach(standard->createCourse.createGrade(standard));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Grade> mapGradeDatafromSheet(Sheet sheet) {
        List<Grade> gradelist = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                Grade g = new Grade();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        g.setId((int) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        g.setName(cell.getStringCellValue());
                    else if (2 == cell.getColumnIndex())
                        g.setOrder((int) cell.getNumericCellValue());
                    else if (3 == cell.getColumnIndex())
                        g.setActive(cell.getBooleanCellValue());
                    else if (4 == cell.getColumnIndex())
                        g.setPicture(cell.getStringCellValue());
                    else if (5 == cell.getColumnIndex())
                        g.setTestId(cell.getStringCellValue());
                    else if (6 == cell.getColumnIndex())
                        g.setTrialVideo(cell.getStringCellValue());
                }
                gradelist.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradelist;
    }

    public List<Subject> mapSubjectDatafromSheet(Sheet sheet) {
        List<Subject> subjectList = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                Subject s = new Subject();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        s.setGradeId((int) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        s.setId((int) cell. getNumericCellValue());
                    else if (2 == cell.getColumnIndex())
                        s.setName(cell.getStringCellValue());
                    else if (3 == cell.getColumnIndex())
                        s.setOrder((int) cell.getNumericCellValue());
                    else if (4 == cell.getColumnIndex())
                        s.setSubjectpic(cell.getStringCellValue());
                    else if (5 == cell.getColumnIndex())
                        s.setTestid(cell.getStringCellValue());
                    else if (6 == cell.getColumnIndex())
                        s.setTrialVideo(cell.getStringCellValue());
                }
                subjectList.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;

    }

    public List<Chapter> mapChapterDatafromSheet(Sheet sheet) {
        List<Chapter> chapterlist = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                Chapter c = new Chapter();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        c.setSubjectId((int) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        c.setId((int) cell. getNumericCellValue());
                    else if (2 == cell.getColumnIndex())
                        c.setChapterName(cell.getStringCellValue());
                    else if (3 == cell.getColumnIndex())
                        c.setChapterPic(cell.getStringCellValue());
                    else if (4 == cell.getColumnIndex())
                        c.setOrder((int) cell.getNumericCellValue());
                    else if (5 == cell.getColumnIndex())
                        c.setTestid(cell.getStringCellValue());
                    else if (6 == cell.getColumnIndex())
                        c.setTrialvideo(cell.getStringCellValue());

                }
                chapterlist.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapterlist;

    }


    public List<ChapterVideo> mapChapterVideoDatafromSheet(Sheet sheet) {
        List<ChapterVideo> chapterVideolist = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                ChapterVideo cv = new ChapterVideo();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        cv.setChapterid((int) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        cv.setVideoname(cell. getStringCellValue());
                    else if (2 == cell.getColumnIndex())
                        cv.setVideoUrl(cell.getStringCellValue());
                    else if (3 == cell.getColumnIndex())
                        cv.setOrder((int) cell.getNumericCellValue());
                    else if (4 == cell.getColumnIndex())
                        cv.setVideoDuration(cell.getStringCellValue());
                    else if (5 == cell.getColumnIndex())
                        cv.setIntialunlock(cell.getBooleanCellValue());
                    else if (6 == cell.getColumnIndex())
                        cv.setTestid(cell.getStringCellValue());

                }
                chapterVideolist.add(cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapterVideolist;
    }

    public void preparedata(){
        grademap.forEach((key,value)->{
            List<Subject> subjectmapping=subjectmap.get(key);
            value.get(0).setSubject(mapChapter(subjectmapping));
        });
    }

    public List<Subject> mapChapter(List<Subject> subject){
        return subject.stream().map(subj->{
            List<Chapter> chapter=chaptermap.get(subj.getId());
            subj.setChapter(mapChapterVideo(chapter));
            return subj;
        }).collect(Collectors.toList());
    }

    public List<Chapter> mapChapterVideo(List<Chapter> chapterlist){
         return chapterlist.stream().map(chapter->{
            chapter.setChaptervideo(chapterVideomap.get(chapter.getId()));
            return chapter;
        }).collect(Collectors.toList());
    }
}
