package org.iStat.api.iController;

import org.iStat.api.iService.DatasetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public @ResponseBody String saveDataset(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        LOG.info("TODO");
        return "teste";
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
