'use strict';

iStat.factory('DocumentiStat', function () {

    function DocumentiStat() {
        this.data = JSON.parse("{ \"datasets\": [ ] }");
    }

    DocumentiStat.prototype.addCell = function (datasetName, line, column, value) {

        var documentiStat = this.data;

        var size = documentiStat.datasets.length;
        var newDataset = size == 0 ? true : false;
        var inserted = false;

        console.log("new ??" + newDataset);

        for (var i = 0; i < size; i++) {
            if (documentiStat.datasets[i].name == datasetName) {
                var valueAux = value == "" ? null: value;
                var newCell = '{"line":"' + line + '","column":"' + column + '","value":' + valueAux + '}';
                documentiStat.datasets[i].cells.push(JSON.parse(newCell));
                inserted = true;
            } else {
                newDataset = true;
            }
        }

        if (newDataset == true && inserted == false) {
            var valueAux = value == "" ? null: value;
            var newDataset = '{"name":"' + datasetName + '","cells":[{"line":"' + line + '","column":"' + column + '","value":' + valueAux + '}]}';
            //Create new dataset
            documentiStat.datasets.push(JSON.parse(newDataset));
            
        }

        this.data = documentiStat; 

    };

    DocumentiStat.prototype.printDocumentiStat = function () {
        console.log(this.data);
    };

    return {
        createNew: function () {
            return new DocumentiStat();
        }
    };
})