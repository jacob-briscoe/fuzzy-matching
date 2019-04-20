package briscoe.fuzzy.matching;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class VehicleTests {

    private static final String SANTA_FE_ABV = "SANTA_FE_ABV";
    private static final String SANTA_FE = "SANTA_FE";
    private static final String PILOT_ABV = "PILOT_ABV";
    private static final String PILOT = "PILOT";
    private static final String ACCORD_SPELL = "ACCORD_SPELL";
    private static final String ACCORD_SPELL2 = "ACCORD_SPELL2";
    private static final String ACCORD = "ACCORD";
    private static final String BMW_428 = "BMW_428";
    private static final String BMW_430 = "BMW_430";
    private static final String CIVIC_LX = "CIVIC_Coupe";
    private static final String CIVIC_EX = "CIVIC_4D";
    private static final String FORD_C_VIC1 = "FordCVic";
    private static final String FORD_C_VIC = "Ford-CVic";
    private static final String FORD_CV = "Ford-CV";
    private static final String NISSAN25SR = "Nissan-25SR";
    private static final String NISSAN_V6 = "Nissan-V6";
    private static final String NISSAN = "Nissan";
    private static final String FORD_F_150 = "Ford-F_150";
    private static final String FORD_F150 = "Ford-F-150";
    private static final String FORD_F150SD = "Ford-F150SD";
    private static final String VW = "VW";
    private static final String VOLKSWAGON = "Volkswagon";
    private static final String VOLKSWAGEN = "Volkswagen";
    private static final String HD1 = "H-D";
    private static final String HD = "HD";
    private static final String HARLEY_D = "Harley-D";
    private static final String HARLEY = "Harley";
    private static final String HARLEY__DAVIDSON = "Harley Davidson";
    private static final String CHEVROLE = "Chevrole";
    private static final String CHEVY = "Chevy";
    private static final String CHEVROLET = "Chevrolet";

    private Map<String, Vehicle> sampleData;

    @BeforeEach
    public void setup() {
        sampleData = new HashMap<>();
        sampleData.put(ACCORD, Vehicle.of("Honda", "Accord"));
        sampleData.put(ACCORD_SPELL, Vehicle.of("HONDA", "Acord"));
        sampleData.put(ACCORD_SPELL2, Vehicle.of("Hona", "Acord"));
        sampleData.put(CIVIC_EX, Vehicle.of("Honda", "Civic EX"));
        sampleData.put(CIVIC_LX, Vehicle.of("Honda", "Civic LX"));
        sampleData.put(PILOT, Vehicle.of("Honda", "Pilot"));
        sampleData.put(PILOT_ABV, Vehicle.of("hOnda", "Plt"));
        sampleData.put(SANTA_FE, Vehicle.of("Hyundai", "Santa Fe"));
        sampleData.put(SANTA_FE_ABV, Vehicle.of("HYUNDAI", "St Fe"));
        sampleData.put(BMW_430, Vehicle.of("BMW", "430i"));
        sampleData.put(BMW_428, Vehicle.of("BMW", "428i"));

        sampleData.put(CHEVROLET, Vehicle.of("Chevrolet", "Cruze"));
        sampleData.put(CHEVY, Vehicle.of("Chevy", "Cruze"));
        sampleData.put(CHEVROLE, Vehicle.of("Chevrole", "Cruze"));

        sampleData.put(HARLEY__DAVIDSON, Vehicle.of("Harley Davidson", "Chopper"));
        sampleData.put(HARLEY, Vehicle.of("Harley", "Chopper"));
        sampleData.put(HARLEY_D, Vehicle.of("Harley-D", "Chopper"));
        sampleData.put(HD, Vehicle.of("HD", "Chopper"));
        sampleData.put(HD1, Vehicle.of("H-D", "Chopper"));

        sampleData.put(VOLKSWAGEN, Vehicle.of("Volkswagen", "Jetta"));
        sampleData.put(VOLKSWAGON, Vehicle.of("Volkswagon", "Jetta"));
        sampleData.put(VW, Vehicle.of("VW", "Jetta"));

        sampleData.put(FORD_F150SD, Vehicle.of("Ford", "F150SD"));
        sampleData.put(FORD_F150, Vehicle.of("Ford", "F-150"));
        sampleData.put(FORD_F_150, Vehicle.of("Ford", "F 150 Super Duty"));

        sampleData.put(NISSAN, Vehicle.of("Nissan", "Altima"));
        sampleData.put(NISSAN_V6, Vehicle.of("Nissan", "Altima-V6"));
        sampleData.put(NISSAN25SR, Vehicle.of("Nissan", "Altima 2.5 SR"));

        sampleData.put(FORD_CV, Vehicle.of("Ford", "Crown Victoria"));
        sampleData.put(FORD_C_VIC, Vehicle.of("Ford", "Crown Vic"));
        sampleData.put(FORD_C_VIC1, Vehicle.of("Ford", "CrownVic"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Vehicle-Similarity.csv", numLinesToSkip = 1)
    public void similarity(final boolean isSimilar,
            final String make, final String model, final String compareToMake,
            final String compareToModel) {
        final Vehicle vehicle = Vehicle.of(make, model);
        final Vehicle other = Vehicle.of(compareToMake, compareToModel);

        final boolean areVehiclesSimilar = vehicle.isSimilar2(other);
        assertEquals(isSimilar, areVehiclesSimilar,
                String.format("Expected isSimilar: %s for %s compared to %s", isSimilar, vehicle, other));
    }
//
//    @Test
//    public void notEvenClose() {
////make [honda:honda] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [accord:pilot] (ratio: 18, partial: 20, tokenSortPartial: 20, tokenSort: 18, tokenSet: 18, tokenSetPartial: 20, tokenWeighted: 18)
////make [honda:hyundai] (ratio: 67, partial: 60, tokenSortPartial: 60, tokenSort: 67, tokenSet: 67, tokenSetPartial: 60, tokenWeighted: 67) 
//// model [accord:santa fe] (ratio: 14, partial: 17, tokenSortPartial: 17, tokenSort: 14, tokenSet: 14, tokenSetPartial: 17, tokenWeighted: 14)
// 
//        assertFalse((vehicle(ACCORD).isSimilar(vehicle(PILOT))));
//        assertFalse((vehicle(ACCORD).isSimilar(vehicle(SANTA_FE))));
//    }
//
//    @Test
//    public void spellingIssues() {
////make [honda:honda] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [accord:acord] (ratio: 91, partial: 80, tokenSortPartial: 80, tokenSort: 91, tokenSet: 91, tokenSetPartial: 80, tokenWeighted: 91)
////make [honda:hona] (ratio: 89, partial: 75, tokenSortPartial: 75, tokenSort: 89, tokenSet: 89, tokenSetPartial: 75, tokenWeighted: 89) 
//// model [accord:acord] (ratio: 91, partial: 80, tokenSortPartial: 80, tokenSort: 91, tokenSet: 91, tokenSetPartial: 80, tokenWeighted: 91)
////make [honda:honda] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [pilot:plt] (ratio: 75, partial: 67, tokenSortPartial: 67, tokenSort: 75, tokenSet: 75, tokenSetPartial: 67, tokenWeighted: 75)
// 
//        assertTrue((vehicle(ACCORD).isSimilar(vehicle(ACCORD_SPELL))));
//        assertTrue((vehicle(ACCORD).isSimilar(vehicle(ACCORD_SPELL2))));
//        assertTrue((vehicle(PILOT).isSimilar(vehicle(PILOT_ABV))));
//    }
//
//    @Test
//    public void slightButMeaningfulDifferences(){
////make [bmw:bmw] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [430i:428i] (ratio: 50, partial: 50, tokenSortPartial: 50, tokenSort: 50, tokenSet: 50, tokenSetPartial: 50, tokenWeighted: 50)
////make [honda:honda] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [civic ex:civic lx] (ratio: 88, partial: 88, tokenSortPartial: 88, tokenSort: 88, tokenSet: 88, tokenSetPartial: 100, tokenWeighted: 88)
// 
//        assertFalse((vehicle(BMW_430).isSimilar(vehicle(BMW_428))));
//        assertFalse((vehicle(CIVIC_EX).isSimilar(vehicle(CIVIC_LX))));
//    }
//    
//    @Test
//    public void dataSample(){
////make [chevrolet:chevy] (ratio: 57, partial: 80, tokenSortPartial: 80, tokenSort: 57, tokenSet: 57, tokenSetPartial: 80, tokenWeighted: 72) 
//// model [cruze:cruze] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [chevrolet:chevrole] (ratio: 94, partial: 100, tokenSortPartial: 100, tokenSort: 94, tokenSet: 94, tokenSetPartial: 100, tokenWeighted: 94) 
//// model [cruze:cruze] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [chevy:chevrole] (ratio: 62, partial: 80, tokenSortPartial: 80, tokenSort: 62, tokenSet: 62, tokenSetPartial: 80, tokenWeighted: 72) 
//// model [cruze:cruze] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
//
////        vehicle(CHEVROLET).isSimilar(vehicle(CHEVY));
////        vehicle(CHEVROLET).isSimilar(vehicle(CHEVROLE));
////        vehicle(CHEVY).isSimilar(vehicle(CHEVROLE));
//        
////make [harley davidson:harley] (ratio: 57, partial: 100, tokenSortPartial: 100, tokenSort: 57, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 90) 
//// model [chopper:chopper] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [harley davidson:harley-d] (ratio: 61, partial: 88, tokenSortPartial: 88, tokenSort: 70, tokenSet: 86, tokenSetPartial: 100, tokenWeighted: 90) 
//// model [chopper:chopper] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [harley davidson:hd] (ratio: 24, partial: 50, tokenSortPartial: 50, tokenSort: 12, tokenSet: 12, tokenSetPartial: 50, tokenWeighted: 45) 
//// model [chopper:chopper] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [harley davidson:h-d] (ratio: 22, partial: 33, tokenSortPartial: 67, tokenSort: 33, tokenSet: 33, tokenSetPartial: 67, tokenWeighted: 60) 
//// model [chopper:chopper] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
// 
////        vehicle(HARLEY__DAVIDSON).isSimilar(vehicle(HARLEY));
////        vehicle(HARLEY__DAVIDSON).isSimilar(vehicle(HARLEY_D));
////        vehicle(HARLEY__DAVIDSON).isSimilar(vehicle(HD));
////        vehicle(HARLEY__DAVIDSON).isSimilar(vehicle(HD1));
//
////make [volkswagen:volkswagon] (ratio: 90, partial: 90, tokenSortPartial: 90, tokenSort: 90, tokenSet: 90, tokenSetPartial: 90, tokenWeighted: 90) 
//// model [jetta:jetta] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
////make [volkswagen:vw] (ratio: 33, partial: 50, tokenSortPartial: 50, tokenSort: 33, tokenSet: 33, tokenSetPartial: 50, tokenWeighted: 45) 
//// model [jetta:jetta] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100)
//
////        vehicle(VOLKSWAGEN).isSimilar(vehicle(VOLKSWAGON));
////        vehicle(VOLKSWAGEN).isSimilar(vehicle(VW));
//
////make [nissan:nissan] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [altima:altima-v6] (ratio: 80, partial: 100, tokenSortPartial: 100, tokenSort: 80, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 90)
////make [nissan:nissan] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [altima:altima 2.5 sr] (ratio: 63, partial: 100, tokenSortPartial: 100, tokenSort: 63, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 90)
//
////        vehicle(NISSAN).isSimilar(vehicle(NISSAN_V6));
////        vehicle(NISSAN).isSimilar(vehicle(NISSAN25SR));
//
////make [ford:ford] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [crown victoria:crown vic] (ratio: 78, partial: 100, tokenSortPartial: 100, tokenSort: 78, tokenSet: 78, tokenSetPartial: 100, tokenWeighted: 90)
////make [ford:ford] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [crown victoria:crownvic] (ratio: 73, partial: 88, tokenSortPartial: 88, tokenSort: 73, tokenSet: 73, tokenSetPartial: 88, tokenWeighted: 79)
//
////        vehicle(FORD_CV).isSimilar(vehicle(FORD_C_VIC));
////        vehicle(FORD_CV).isSimilar(vehicle(FORD_C_VIC1));
//
////make [ford:ford] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [f-150:f150sd] (ratio: 73, partial: 80, tokenSortPartial: 60, tokenSort: 55, tokenSet: 55, tokenSetPartial: 60, tokenWeighted: 73)
////make [ford:ford] (ratio: 100, partial: 100, tokenSortPartial: 100, tokenSort: 100, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 100) 
//// model [f-150:f 150 super duty] (ratio: 38, partial: 80, tokenSortPartial: 80, tokenSort: 48, tokenSet: 100, tokenSetPartial: 100, tokenWeighted: 90)
////        vehicle(FORD_F150).isSimilar(vehicle(FORD_F150SD));
////        vehicle(FORD_F150).isSimilar(vehicle(FORD_F_150));
//    }
//
//    private Vehicle vehicle(final String vehicleId) {
//        return sampleData.get(vehicleId);
//    }

}
