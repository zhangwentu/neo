package com.neo.easypoi.util;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/4/3 13:40
 */
@Slf4j
public class ExcelExportUtils {
    public static void exportExcel(HttpServletResponse response, String fileName, Workbook workbook) {
        OutputStream out = null;
        try {
            fileName = fileName + DateUtil.format(new Date(), "YYYY-MM-DD") + ".xlsx";
            log.info("[导出Excel] 开始导出EXCEL {}", fileName);
            response.reset();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            workbook.write(byteArrayOutputStream);
//            response.setHeader("Content-Length", byteArrayOutputStream.size() + "");

            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            log.error("[导出Excel] 导出数据异常", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            log.info("[导出Excel] 导出EXCEL结束 {}", fileName);
        }
    }

}
