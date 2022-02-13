package com.example.demo.handler;

import com.example.demo.model.Complex;
import com.example.demo.model.ComplexOperator;
import com.example.demo.model.imports.JsonImport;
import com.example.demo.service.JsonImportService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.core.exception.EruptApiErrorTip;
import xyz.erupt.core.view.EruptApiModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liyuepeng
 * @date 2018-10-10.
 */
@Log4j2
@Component
public class JsonImportOperationHandlerImpl implements OperationHandler<JsonImport, JsonImport> {

    @Autowired
    JsonImportService jsonImportService;
    @Override
    public String exec(List<JsonImport> data, JsonImport jsonImport, String[] param) {
        String ans = "";
        for (JsonImport jsonImportdata: data){
            try {
                ans+=jsonImportService.importJsonData(jsonImportdata);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        System.out.println(ans);
        return ans;
    }
}
