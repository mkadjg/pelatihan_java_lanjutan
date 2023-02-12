package com.pub.course.service;

import com.pub.course.model.Guru;
import com.pub.course.repository.GuruRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SpreadsheetServiceImpl implements SpreadsheetService {

    GuruRepository guruRepository;
    @Autowired
    SpreadsheetServiceImpl(GuruRepository guruRepository) {
        this.guruRepository = guruRepository;
    }

    @Override
    public void generate(OutputStream outputStream) throws IOException {
        List<Guru> guruList = guruRepository.findAll();

        // create workbook
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            // create sheet
            Sheet sheet = workbook.createSheet();

            // setup header font
            XSSFFont headerFont = workbook.createFont();
            headerFont.setFontName("Inter");
            headerFont.setFontHeightInPoints((short) 11);
            headerFont.setBold(true);

            // cell style for header row
            CellStyle infoStyle = workbook.createCellStyle();
            infoStyle.setFont(headerFont);
            infoStyle.setAlignment(HorizontalAlignment.LEFT);
            infoStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            infoStyle.setWrapText(true);

            // create row
            Row headerRow = sheet.createRow(0);

            // create cell
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("ID Guru");
            headerCell.setCellStyle(infoStyle);

            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("Nama Guru");
            headerCell.setCellStyle(infoStyle);

            headerCell = headerRow.createCell(2);
            headerCell.setCellValue("NIP");
            headerCell.setCellStyle(infoStyle);

            headerCell = headerRow.createCell(3);
            headerCell.setCellValue("No Telepon");
            headerCell.setCellStyle(infoStyle);

            headerCell = headerRow.createCell(4);
            headerCell.setCellValue("Pelajaran");
            headerCell.setCellStyle(infoStyle);

            // body font
            XSSFFont bodyFont = workbook.createFont();
            bodyFont.setFontName("Inter");
            bodyFont.setFontHeightInPoints((short) 10);

            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setFont(bodyFont);
            bodyStyle.setAlignment(HorizontalAlignment.LEFT);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setWrapText(true);

            AtomicInteger rowIndex = new AtomicInteger(1);
            guruList.forEach(guru -> {
                Row bodyRow = sheet.createRow(rowIndex.get());

                // create cell
                Cell bodyCell = bodyRow.createCell(0);
                bodyCell.setCellValue(guru.getId());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(1);
                bodyCell.setCellValue(guru.getNamaGuru());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(2);
                bodyCell.setCellValue(guru.getNip());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(3);
                bodyCell.setCellValue(guru.getNoTelepon());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(4);
                bodyCell.setCellValue(guru.getPelajaran());
                bodyCell.setCellStyle(bodyStyle);

                rowIndex.addAndGet(1);
            });

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

            // write into output stream
            workbook.write(outputStream);
        }
    }
}
