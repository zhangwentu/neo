package com.neo.math;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 离散数据
 *
 * @author neo
 * @date 2021/12/17 15:51
 */
public class Discrete {

    /**
     * 计算数学期望
     */
    public static BigDecimal culExpectation(List<BigDecimal> list) {
        // 计算每个性能数据出现的次数
        Map<BigDecimal, Integer> map = new HashMap<>();
        if (list != null && list.size() > 0) {

            for (BigDecimal temp : list) {
                Integer count = map.get(temp);
                map.put(temp, (count == null) ? 1 : count + 1);
            }
            System.out.println(map);
        }
        // 根据离散数学去计算数学期望值，即均值
        BigDecimal ex = BigDecimal.ZERO;
        for (Map.Entry<BigDecimal, Integer> entry : map.entrySet()) {
            //      （测试）数学期望均值  25x0.9+10x0.05+100x0.05=28
            // 性能数据的值
            BigDecimal keyVal = entry.getKey();
            // 这个数据出现的次数
            Integer valueVal = entry.getValue();
            // 计算改数据的权重
            Integer total = list.size();
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(4);
            String val = numberFormat.format((float) valueVal / (float) total);
            BigDecimal exMap = keyVal.multiply(new BigDecimal(val));
            // 加权求期望
            ex = ex.add(exMap);
        }
        return ex;

    }

    /**
     * 计算区间
     */
    public static void culInterval(List<Integer> nums) {

    }

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("/Users/zwt/Desktop/text.xlsx");
        List<Object> nums = reader.readColumn(6, 1);
        List<BigDecimal> list = nums.stream()
                .map(num -> new BigDecimal(num.toString()).setScale(0, BigDecimal.ROUND_HALF_UP))
                .collect(Collectors.toList());
        System.out.println(culExpectation(list).setScale(0, BigDecimal.ROUND_HALF_UP));
    }

}
