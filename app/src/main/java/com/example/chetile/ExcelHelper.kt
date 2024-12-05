package com.example.chetile

import android.content.Context
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class ExcelHelper(val context: Context) {

    private val fileName = "Chetile.xlsx"

    // Создание нового Excel файла, если его нет
    fun createNewExcelFile() {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Лист1")
            val headerRow = sheet.createRow(0)
            headerRow.createCell(0).setCellValue("Столбец")
            headerRow.createCell(1).setCellValue("День")
            headerRow.createCell(2).setCellValue("Значение")
            try {
                FileOutputStream(file).use {
                    workbook.write(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Обновление ячейки в Excel
    fun updateCell(sheetName: String, cell: String, value: String) {
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            val workbook = WorkbookFactory.create(FileInputStream(file))
            val sheet = workbook.getSheet(sheetName)
            val rowIndex = cell.substring(1).toInt() - 1
            val colIndex = cell[0] - 'A'

            val row = sheet.getRow(rowIndex) ?: sheet.createRow(rowIndex)
            val cellToUpdate = row.getCell(colIndex) ?: row.createCell(colIndex)

            cellToUpdate.setCellValue(value)

            try {
                FileOutputStream(file).use {
                    workbook.write(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
