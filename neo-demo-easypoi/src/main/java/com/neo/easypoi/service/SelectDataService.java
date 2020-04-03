package com.neo.easypoi.service;

import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.alibaba.fastjson.JSONObject;
import com.neo.easypoi.vo.ShowVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/4/3 14:04
 */
@Service
public class SelectDataService implements IExcelExportServer {
    @Override
    public List<Object> selectListForExcelExport(Object queryParams, int page) {
        if (page > 100) {
            return null;
        }
        List<ShowVO> list = new ArrayList<>();
        for (int i = 0; i < (int) queryParams; i++) {
            String index = String.valueOf(page * 100 + i);
            list.add(new ShowVO("deptName" + index,
                    "assetName" + index,
                    "oldAssetName" + index,
                    "brand" + index,
                    "model" + index));
        }
        String listJson = JSONObject.toJSONString(list);
        return JSONObject.parseArray(listJson, Object.class);
    }

    public List<ShowVO> selectShowVOForExcelExport(int pageNo, int pageNum) {
        if (pageNo > 100) {
            return null;
        }
        List<ShowVO> list = new ArrayList<>();
        for (int i = 0; i < pageNum; i++) {
            String index = String.valueOf(pageNo * pageNum + i);
            list.add(new ShowVO("deptName" + index,
                    "assetName" + index,
                    "oldAssetName" + index,
                    "brand" + index,
                    "model" + index));
        }
        return list;
    }
}
