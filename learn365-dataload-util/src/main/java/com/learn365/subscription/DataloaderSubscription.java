package com.learn365.subscription;

import com.learn365.model.subscription.OfferGrade;
import com.learn365.model.subscription.OfferPlan;
import com.learn365.model.subscription.OfferSubject;
import com.learn365.util.ReadExcelData;
import org.apache.poi.ss.usermodel.*;
import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataloaderSubscription {

    Map<Long, OfferGrade> offergrademap = new HashMap<>();
    Map<Long, List<OfferPlan>> subsPlanmap = new HashMap<>();
    Map<Integer, List<OfferSubject>> subssubmap = new HashMap<>();
    @Autowired
    private ReadExcelData readExcelData;
    @Autowired
    private PrepareSubscriptionDataRequest prepareDataRequest;
    @Autowired
    private CreateSubscription createCourse;



    public void startMapping() {
        try {
            ClassLoader classLoader = DataloaderSubscription.class.getClassLoader();
            readExcelData = new ReadExcelData();
            File f=new File(classLoader.getResource("SubscriptionData.xlsx").getFile());
            Workbook workbook = readExcelData.getWorkBookSheet(f, 0);

            List<OfferGrade> gradelist = mapOfferGradeDatafromSheet(workbook.getSheetAt(0));
            gradelist.forEach(offergrade-> offergrademap.put(offergrade.getGradeId(),offergrade));
            System.out.println(offergrademap);

            //Subject
            List<OfferPlan> subjectlist = mapOfferPlanDatafromSheet(workbook.getSheetAt(1));
            subsPlanmap = subjectlist.stream().collect(Collectors.groupingBy(OfferPlan::getOfferGradeID));
            System.out.println(subsPlanmap);

            //chapter
            List<OfferSubject> chapterlist = mapOfferSubjectDatafromSheet(workbook.getSheetAt(2));
            subssubmap = chapterlist.stream().collect(Collectors.groupingBy(OfferSubject::getOffered_planId));
            System.out.println(subssubmap);

            preparedata();
            System.out.println("After--------"+offergrademap);
            List<OfferedGradeRequest> standardreq=prepareDataRequest.createCoursePortfolio(offergrademap);
            standardreq.forEach(standard->createCourse.createGrade(standard));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OfferGrade> mapOfferGradeDatafromSheet(Sheet sheet) {
        List<OfferGrade> gradelist = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                OfferGrade g = new OfferGrade();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        g.setId((long) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        g.setOrder((int) cell.getNumericCellValue());
                    else if (2 == cell.getColumnIndex())
                        g.setActive(cell.getBooleanCellValue());
                    else if (3 == cell.getColumnIndex())
                        g.setAppName(cell.getStringCellValue());
                    else if (4 == cell.getColumnIndex())
                        g.setGradeId((long) cell.getNumericCellValue());
                    else if (5 == cell.getColumnIndex())
                        g.setGradeName(cell.getStringCellValue());
                    else if (6 == cell.getColumnIndex())
                        g.setOfferedDescription(cell.getStringCellValue());
                    else if (7 == cell.getColumnIndex())
                        g.setOfferedPicture(cell.getStringCellValue());
                    else if (7 == cell.getColumnIndex())
                        g.setOfferedVideo(cell.getStringCellValue());
                }
                gradelist.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradelist;
    }

    private List<OfferPlan> mapOfferPlanDatafromSheet(Sheet sheet) {
        List<OfferPlan> subjectList = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                OfferPlan s = new OfferPlan();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        s.setId((long) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        s.setActive(cell.getBooleanCellValue());
                    else if (2 == cell.getColumnIndex())
                        s.setAppName(cell.getStringCellValue());
                    else if (3 == cell.getColumnIndex())
                        s.setCost((double) cell.getNumericCellValue());
                    else if (4 == cell.getColumnIndex())
                        s.setCurrency(cell.getStringCellValue());
                    else if (5 == cell.getColumnIndex())
                        s.setDiscountPrice((double) cell.getNumericCellValue());
                    else if (6 == cell.getColumnIndex())
                        s.setOrder((int)cell.getNumericCellValue());
                    else if (7 == cell.getColumnIndex())
                        s.setSubscriptionPlanName(cell.getStringCellValue());
                    else if (8 == cell.getColumnIndex())
                        s.setValidity((int)cell.getNumericCellValue());
                    else if (9 == cell.getColumnIndex())
                        s.setOfferGradeID((long)cell.getNumericCellValue());
                }
                subjectList.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;

    }

    private List<OfferSubject> mapOfferSubjectDatafromSheet(Sheet sheet) {
        List<OfferSubject> chapterlist = new ArrayList<>();
        try {
            Iterator rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                // Skip header
                if (row.getRowNum() == 0)
                    continue;
                Iterator cells = row.cellIterator();
                OfferSubject c = new OfferSubject();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    if (0 == cell.getColumnIndex())
                        c.setId((long) cell.getNumericCellValue());
                    else if (1 == cell.getColumnIndex())
                        c.setActive(cell. getBooleanCellValue());
                    else if (2 == cell.getColumnIndex())
                        c.setAppName(cell.getStringCellValue());
                    else if (3 == cell.getColumnIndex())
                        c.setOfferedSubjectDesccription(cell.getStringCellValue());
                    else if (4 == cell.getColumnIndex())
                        c.setOfferedSubjectid((long) cell.getNumericCellValue());
                    else if (5 == cell.getColumnIndex())
                        c.setOfferedSubjectname(cell.getStringCellValue());
                    else if (6 == cell.getColumnIndex())
                        c.setOrder((int) cell.getNumericCellValue());
                    else if (7 == cell.getColumnIndex())
                        c.setSubjectPicture(cell.getStringCellValue());
                    else if (8 == cell.getColumnIndex())
                        c.setOffered_planId((int)cell.getNumericCellValue());

                }
                chapterlist.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapterlist;

    }



    public void preparedata(){
        offergrademap.forEach((key,value)->{
            List<OfferPlan> offerGrademapping=subsPlanmap.get(key);
            value.setOfferplan(mapChapter(offerGrademapping));
        });
    }

    public List<OfferPlan> mapChapter(List<OfferPlan> offerPlans){
        return offerPlans.stream().map(offplan->{
            List<OfferSubject> offerSubject=subssubmap.get(offplan.getId().intValue());
            offplan.setOfferSubjects(offerSubject);
            return offplan;
        }).collect(Collectors.toList());
    }


}
