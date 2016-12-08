package org.iStat.api.iController;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iService.DatasetService;
import org.iStat.api.iStatIO.request.RequestiStatSave;
import org.iStat.api.iStatIO.response.ResponseiStatSave;
import org.iStat.api.response.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatDataset")
public class IStatDataset {

    private final Logger LOG = LoggerFactory.getLogger(IStatDataset.class);

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private Converter<RequestiStatSave, DocumentiStat> converterRequestiStatSave;

    @Autowired
    private Converter<Boolean, ResponseiStatSave> converterResponseiStatSave;

    /**
     * @TODO
     *       WebService responsible for saving a dataset.
     * 
     *       URL example:
     *       http://localhost:8080/iStatDataset/saveDataset...
     * 
     * @param x
     * @return JSON of status and result
     */
    @RequestMapping(value = "/saveDataset", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatSave saveDataset(@RequestBody RequestiStatSave request) {
        DocumentiStat documentiStat = converterRequestiStatSave.convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Boolean result = datasetService.saveDataset(documentiStat);

        ResponseiStatSave response = converterResponseiStatSave.convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * @TODO
     *       WebService responsible for getting a dataset.
     * 
     *       URL example:
     *       http://localhost:8080/iStatDataset/getDataset...
     * 
     * @param x
     * @return JSON of status and result
     */
    @RequestMapping(value = "/getDataset", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody String getDataset(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        LOG.info("TODO");
        return "teste";
    }

}
