package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGridTest {
    @Test
    public void getZoneAt() throws Exception {
        Grid<Hex> grid = new HexGrid(15);
        List<GeoPoint> points = Arrays.asList(new GeoPoint(51.631907, 69.882734), new GeoPoint(53.870121, 27.550767),
                new GeoPoint(39.822484, -89.721609), new GeoPoint(-12.173478, -54.291088), new GeoPoint(-9.780389, 29.084406),
                new GeoPoint(-24.190612, 144.314865));
        for (GeoPoint point : points) {
            Hex hex = grid.getZoneAt(point);
            System.out.println("--------------------------");
            System.out.println(point);
            hex.getPolygon().stream()
                    .map(x -> "(" + x.lat + "," + x.lon + ")")
                    .reduce((a, b) -> a + ", " + b)
                    .ifPresent(System.out::println);
        }
    }

}