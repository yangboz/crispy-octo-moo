angular.module('starter.services', [])
    .factory('EITCCreditService', function () {
        // Might use a resource here that returns a JSON array

        //var numberofChildren = parseInt(Session.get('numberofChildren'));
        //var income = parseInt(Session.get('income'));
        //var relationship_status = Session.get('relationship_status');
        //console.log('numberofchildren income relationship_status:' + numberofChildren + ' ' + income + ' ' + relationship_status);
        var incomeFixture = [20330, 44651, 49974, 53267];
        var numberofChildrenThresholds = [0, 1, 2, 3];
        var creditFixture = ['$503', '$3359', '$5548', '$6242', 'unknown'];
        var eitccreditdetail = 'EITC Credit:';

        return {
            get: function ($relationship_status, $numberofChildren, $income) {

                if ($relationship_status == 'Married') {
                    for (var i = 0; i < numberofChildrenThresholds.length; i++) {
                        if ($income < incomeFixture[i]) {
                            eitccreditdetail = 'EITC Credit:' + creditFixture[i];
                        } else {
                            eitccreditdetail = 'EITC Credit:' + ' unknown';
                        }
                    }
                } else {
                    eitccreditdetail = 'EITC Credit:' + ' unknown';
                }
                return eitccreditdetail;
            }
        }
    })
    .factory('IncomeCategoryService', function () {
        var categoriesFixture = [
            ["0-150000", "300000-700000", "700000-10000000", "1000000-1500000"],
            ["150000-300000", "300000-700000", "700000-10000000", "1000000-1500000"]
        ];

        return {
            get: function ($relationship_status) {

                return categoriesFixture[($relationship_status == 'Married')];
            }
        }
    })
    .factory('FilingCategoryService', function () {
        var categoriesFixture = [
            ["filing jointly", "filing separatly"],
            ["single filer", "head of household"]
        ];

        return {
            get: function ($relationship_status) {

                return categoriesFixture[($relationship_status == 'Married')];
            }
        }
    })
    .factory('FilingCategoryService', function () {
        var categoriesFixture = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];

        return {
            all: function () {
                return categoriesFixture;
            }
        }
    })
    .factory('EVCreditService', function () {
        // Might use a resource here that returns a JSON array

        // Some fake testing data
        var ElecticVehicleCredits = {
            'Accord Plug-In Hybrid': 3626,
            'Azure Dynamics Transit Connect Electric Vehicle': 7500,
            'BMW i3 Sedan': 7500,
            'BMW i8': 3793,
            'Boulder Electric DV-500': 7500,
            'BYD e6 Electric Vehicle': 7500,
            'Fiat 500e': 7500,
            'CODA Sedan': 7500,
            'Electric Vehicles International (EVI) Electric truck': 7500,
            'EMC Model E36 (Electric Vehicle Manufactured by Electric Mobile Cars)': 7500,
            'Fisker Karma': 7500,
            'Ford Focus Electric': 7500,
            'Ford C-MAX Energi': 4007,
            'Ford Fusion Energi': 4007,
            'Cadillac ELR': 7500,
            'Chevrolet Volt': 7500,
            'Chevrolet Spark EV': 7500,
            'Kia Soul Electric': 7500,
            'Mercedes-Benz smart Coupe/Cabrio EV': 7500,
            'Mercedes-Benz B-Class EV': 7500,
            'Mitsubishi i-MiEV': 7500,
            'Nissan Leaf': 7500,
            'Porsche 918 Spyder': 3667,
            'Porsche Panamera S E Hybrid': 4751.80,
            'Porsche Caynee S E-Hybrid': 5335.60,
            'smart fortwo': 7500,
            'Tesla Roadster': 7500,
            'Tesla Model S': 7500,
            'Think City EV': 7500,
            'Toyota Prius Plug-in Electic Drive Vehicle': 2500,
            'Toyota RAV4 EV': 7500,
            'VIA 2500': 7500,
            'VIA 1500': 7500,
            'Volkswagen e-Golf': 7500,
            'Wheego LiFe Electric Vehicle': 7500,
            'Zenith Electric Van': 7500
        };
        var MortgageInterest = ["MORTGAGE", "HOME OWNER", "OUR HOUSE", "NEW HOUSE", "MOVING TO A NEW HOUSE"];

        var Children = ["new baby", "my daughter", "my son"];

        return {
            get: function ($key) {
                return ElecticVehicleCredits[$key];
            }
        };
    });
