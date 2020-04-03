package com.neo.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.collection.CollectionUtil;
import com.neo.easypoi.service.SelectDataService;
import com.neo.easypoi.util.ExcelExportUtils;
import com.neo.easypoi.vo.ShowVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/30 17:42
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private SelectDataService selectDataService;

    /**
     * easypoi version : 3.2.0
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel3x(HttpServletResponse response) {
        ExportParams exportParams = new ExportParams();
        int pageNo = 0;
        int pageNum = 1000;
        List<ShowVO> list = selectDataService.selectShowVOForExcelExport(pageNo, pageNum);
        Workbook workbook = null;
        while (CollectionUtil.isNotEmpty(list)) {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, ShowVO.class, list);
            list = selectDataService.selectShowVOForExcelExport(++pageNo, pageNum);
        }
        if (null == workbook) {
            return;
        }
        ExcelExportUtil.closeExportBigExcel();
        ExcelExportUtils.exportExcel(response, "转出", workbook);
    }

    /**
     * easypoi version : 4.1.0
     */
//    @RequestMapping("/exportExcel")
//    @ResponseBody
//    public void exportExcel4x(HttpServletResponse response) {
//        ExportParams exportParams = new ExportParams();
//        Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, ShowVO.class, selectDataService, 1);
//        ExcelExportUtils.exportExcel(response, "转出", workbook);
//    }

}
