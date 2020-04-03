package com.neo.easypoi.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/4/3 13:47
 */
@Data
public class ShowVO {
    public ShowVO() {
    }

    public ShowVO(String deptName, String assetName, String oldAssetName, String brand, String model) {
        this.deptName = deptName;
        this.assetName = assetName;
        this.oldAssetName = oldAssetName;
        this.brand = brand;
        this.model = model;
    }

    @Excel(name = "单位名称", orderNum = "1", width = 20.0)
    private String deptName;
    @Excel(name = "资产名称", orderNum = "2", width = 20.0)
    private String assetName;
    @Excel(name = "原资产名称", orderNum = "3", width = 20.0)
    private String oldAssetName;
    @Excel(name = "资产品牌", orderNum = "4", width = 20.0)
    private String brand;
    @Excel(name = "资产规格型号", orderNum = "5", width = 20.0)
    private String model;
}
