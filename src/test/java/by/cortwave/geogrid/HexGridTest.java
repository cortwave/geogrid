package by.cortwave.geogrid;

import by.cortwave.geogrid.constant.GeoConstants;
import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import by.cortwave.geogrid.shape.Triangle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGridTest {
    @Test
    public void getZoneAt() throws Exception {
        Grid<Hex> grid = new HexGrid(11);
        Hex hex = grid.getZoneAt(new GeoPoint(53.870121, 27.550767));
        System.out.println(hex);
        hex.getPolygon().forEach(System.out::println);
    }

}