package com.charity.common.util;

import com.charity.entity.canteen.Order;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JiaoZhiPeng on 2018/6/14.
 */
public class OrderListExcleExport extends AbstractExcelView {

    private Map<String, List<Order>> map = new HashMap<>();

    @Override
    protected void buildExcelDocument(Map<String, Object> mode, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        workbook = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
        workbook.createSheet();

        workbook.setSheetName(0,mode.get("sheetName").toString());
        sheet.createFreezePane(1, 3);// 冻结
        // 设置列宽
        sheet.setColumnWidth(0, 3500);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 7000);
        // Sheet样式
        HSSFCellStyle sheetStyle = workbook.createCellStyle();
/*        // 背景色的设定
        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 前景色的设定
        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 填充模式
        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);*/
        // 设置列的样式
        for (int i = 0; i <= 14; i++) {
            sheet.setDefaultColumnStyle((short) i, sheetStyle);
        }
        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("黑体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 另一个样式
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        headstyle.setWrapText(true);// 自动换行
        // 另一个字体样式
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 列头的样式
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // 普通单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        // 另一个样式
        HSSFCellStyle centerstyle = workbook.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

        try {
            HSSFSheet sheet1 = workbook.getSheet(mode.get("sheetName").toString());
            sheet1.setColumnWidth(0, 7000);
            sheet1.setColumnWidth(1, 7000);
            sheet1.setColumnWidth(2, 7000);
            sheet1.setColumnWidth(3, 7000);
            // 创建第一行
            HSSFRow row0 = sheet1.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);
            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("订单统计"));
            cell0.setCellStyle(headstyle);
            /**
             * 合并单元格
             *    第一个参数：第一个单元格的行数（从0开始）
             *    第二个参数：第二个单元格的行数（从0开始）
             *    第三个参数：第一个单元格的列数（从0开始）
             *    第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0,3);
            sheet1.addMergedRegion(range);
            // 创建第二行
            HSSFRow row2 = sheet1.createRow(1);
            row2.setHeight((short) 750);
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue(new HSSFRichTextString("订单号"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(1);
            cell.setCellValue(new HSSFRichTextString("订餐人"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(2);
            cell.setCellValue(new HSSFRichTextString("订单金额"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(3);
            cell.setCellValue(new HSSFRichTextString("创建时间"));
            cell.setCellStyle(columnHeadStyle);
            // 访问数据库，得到数据集
            List<Order> orders = (List<Order>) map.get("orderList");
            for (int i = 0; i < orders.size(); i++) {
                HSSFRow row = sheet1.createRow(i+2);
                cell = row.createCell(0);
                cell.setCellValue(orders.get(i).getId().toString());
                cell.setCellStyle(centerstyle);
                cell = row.createCell(1);
                cell.setCellValue(orders.get(i).getUserName());
                cell.setCellStyle(centerstyle);
                cell = row.createCell(2);
                cell.setCellValue(new HSSFRichTextString(orders.get(i).getAmt().toString()));
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(new HSSFRichTextString(TimeFormat.format("yyyy-MM-dd HH:mm:ss",orders.get(i).getCreateTime())));
                cell.setCellStyle(style);
            }
            HSSFRow row = sheet1.createRow(orders.size()+2);
            cell = row.createCell(0);
            cell.setCellValue("订单总计数量");
            cell.setCellStyle(centerstyle);
            cell = row.createCell(1);
            cell.setCellValue(orders.size()+"个");
            cell.setCellStyle(centerstyle);
            HSSFRow row1 = sheet1.createRow(orders.size()+3);
            cell = row1.createCell(0);
            cell.setCellValue("订单总计金额");
            cell.setCellStyle(centerstyle);
            cell = row1.createCell(1);
            cell.setCellValue(mode.get("amt").toString()+"元");
            cell.setCellStyle(centerstyle);
            String filename = mode.get("fileName").toString()+ ".xls";//设置下载时客户端Excel的名称
            filename = OrderListExcleExport.encodeFilename(filename, request);//处理中文文件名
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderListExcleExport(Map<String, List<Order>> map) {
        this.map = map;
    }

    /**
     * 设置下载文件中文件的名称
     *
     * @param filename
     * @param request
     * @return
     */
    public static String encodeFilename(String filename, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }

            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }
}
