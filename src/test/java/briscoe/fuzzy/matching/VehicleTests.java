package briscoe.fuzzy.matching;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class VehicleTests {

    @ParameterizedTest
    @CsvFileSource(resources = "/Vehicle-Similarity.csv", numLinesToSkip = 1)
    public void similarity(final boolean isSimilar,
            final String make, final String model, final String compareToMake,
            final String compareToModel) {
        final Vehicle vehicle = Vehicle.of(make, model);
        final Vehicle other = Vehicle.of(compareToMake, compareToModel);

        final boolean areVehiclesSimilar = vehicle.isSimilarTo(other);
        assertEquals(isSimilar, areVehiclesSimilar,
                String.format("Expected isSimilar: %s for %s compared to %s", isSimilar, vehicle, other));
    }

}
