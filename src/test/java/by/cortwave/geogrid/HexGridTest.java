package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import org.junit.Test;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGridTest {
    @Test
    public void getZoneAt() throws Exception {
        Grid<Hex> grid = new HexGrid(15);
        Hex hex = grid.getZoneAt(new GeoPoint(53.870121, 27.550767));
        System.out.println(hex);
        hex.getPolygon().forEach(System.out::println);
        System.out.println(hex.getPolygon().get(0).getDistanceTo(hex.getPolygon().get(2)));
        System.out.println(hex.getPolygon().get(2).getDistanceTo(hex.getPolygon().get(1)));
        System.out.println(hex.getPolygon().get(1).getDistanceTo(hex.getPolygon().get(4)));
        System.out.println(hex.getPolygon().get(4).getDistanceTo(hex.getPolygon().get(5)));
        System.out.println(hex.getPolygon().get(5).getDistanceTo(hex.getPolygon().get(3)));
        System.out.println(hex.getPolygon().get(3).getDistanceTo(hex.getPolygon().get(0)));
    }

}