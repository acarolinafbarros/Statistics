package org.iStat.api.iController;

import org.iStat.api.iService.DatasetService;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;
import org.iStat.api.iStatIO.response.ResponseiStatOpen;
import org.iStat.api.iStatIO.response.ResponseiStatSave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatDataset")
public class IStatDataset {

    private final Logger LOGGER = LoggerFactory
        .getLogger(IStatDataset.class);

    @Autowired
    private DatasetService datasetService;

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
    @RequestMapping(value = "/saveDataset", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ResponseiStatSave> saveDataset(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        throw new UnsupportedOperationException("Need to implement!");
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
    public ResponseEntity<ResponseiStatOpen> getDataset(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        throw new UnsupportedOperationException("Need to implement!");
    }

}
