package com.weisi.veems.controller.mpcv;
import com.github.pagehelper.PageInfo;
import com.weisi.common.JsonResult;
import com.weisi.veems.models.elasticsearch.Mpcv;
import com.weisi.veems.services.mpcv.MpCvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @description:历史抄表示数
 * @author:@luomouren.
 * @Date:2018-04-09 16:53
 */
@RestController
@RequestMapping({"/mpcv"})
public class MpCvController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MpCvService mpCvService;
    private static String PAGE_FILE_NAME = "bzh/mpcv/";

    @RequestMapping(value = {"/index"})
    public String index(@RequestParam(value = "cvId", required = true) String cvId,
                        @RequestParam(value = "pageNum", required = true, defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", required = true, defaultValue = "2") int pageSize,
                        ModelMap model) {
        PageInfo<Mpcv> pageInfo = mpCvService.searchMpCv(cvId);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("users", pageInfo.getList());

        model.put("pageInfo", pageInfo);
        return PAGE_FILE_NAME + "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String add() {
        String cvId = "cvId";
        String mpId = "mpId";
        Double value = 12.1230;
        Date dataTime = new Date();
        Date samTime = new Date();
        Mpcv mpcv = new Mpcv(UUID.randomUUID() + "", cvId, mpId, value, dataTime, samTime);
        mpCvService.save(mpcv);
        return PAGE_FILE_NAME+"form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "cvId", required = true)  String cvId) {
        Mpcv mpcv = new Mpcv();
        mpcv.setId("sLPruWIB64xKF3zA_woz");
        mpcv.setCvId(cvId);
        mpcv.setDataTime(new Date());
        mpcv.setValue(new Double("123"));
        mpCvService.update(mpcv);
        return PAGE_FILE_NAME+"form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(@RequestParam(value = "cvId", required = true)  String cvId) {
        mpCvService.deleteByPrimaryKey(cvId);
        try {
            mpCvService.deleteByPrimaryKey(cvId);
        } catch (Exception e) {
            logger.error(e.toString());
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = {"/searchMpCv"}, method = RequestMethod.GET)
    public String searchMpCv(@RequestParam(value = "cvId", required = true) String cvId){
        PageInfo<Mpcv> pageInfo = mpCvService.searchMpCv(cvId);
        return PAGE_FILE_NAME + "index";
    }

}
