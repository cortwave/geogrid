package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGridTest {
    @Test
    public void getZoneAt_detalization_4_id() throws Exception {
        List<TestCaseId> cases = Arrays.asList(
                new TestCaseId(new GeoPoint(51.631907, 69.882734), "NE1231:NE1233:NE1234:NE1432:NE1433:NE1434"),
                new TestCaseId(new GeoPoint(53.870121, 27.550767), "NE1211:NE1213:NE1214:NE1422:NE1423:NE1424"),
                new TestCaseId(new GeoPoint(39.822484, -89.721609), "NW1233:NW1322:NW1433:NW2122:NW2211:NW2422"),
                new TestCaseId(new GeoPoint(-12.173478, -54.291088), "SW1311:SW1312:SW1314:SW1411:SW1413:SW1414"),
                new TestCaseId(new GeoPoint(-9.780389, 29.084406), "SE1131:SE1132:SE1134:SE1141:SE1143:SE1144"),
                new TestCaseId(new GeoPoint(-24.190612, 144.314865), "SE2312:SE2321:SE2342:SE2413:SE2431:SE2441")
        );

        HexGrid grid = new HexGrid(4);
        for (TestCaseId testCase : cases) {
            Assert.assertEquals(testCase.hexId, grid.getZoneAt(testCase.point).getId());
        }
    }

    @Test
    public void getZoneAt_detalization_8_id() throws Exception {
        List<TestCaseId> cases = Arrays.asList(
                new TestCaseId(new GeoPoint(51.631907, 69.882734), "NE12313333:NE12331111:NE12341111:NE14323333:NE14332222:NE14343333"),
                new TestCaseId(new GeoPoint(53.870121, 27.550767), "NE12131312:NE12131313:NE12131314:NE12131341:NE12131342:NE12131344"),
                new TestCaseId(new GeoPoint(39.822484, -89.721609), "NW13222332:NW13222333:NW13222334:NW21222111:NW21222112:NW21222114"),
                new TestCaseId(new GeoPoint(-12.173478, -54.291088), "SW13121111:SW13121113:SW13121114:SW13142222:SW13142223:SW13142224"),
                new TestCaseId(new GeoPoint(-9.780389, 29.084406), "SE11342311:SE11342312:SE11342314:SE11342411:SE11342413:SE11342414"),
                new TestCaseId(new GeoPoint(-24.190612, 144.314865), "SE24133221:SE24133222:SE24133224:SE24134331:SE24134333:SE24134334")
        );

        HexGrid grid = new HexGrid(8);
        for (TestCaseId testCase : cases) {
            Assert.assertEquals(testCase.hexId, grid.getZoneAt(testCase.point).getId());
        }
    }

    @Test
    public void getZoneAt_detalization_12_id() throws Exception {
        List<TestCaseId> cases = Arrays.asList(
                new TestCaseId(new GeoPoint(51.631907, 69.882734), "NE143433333121:NE143433333123:NE143433333124:NE143433333142:NE143433333143:NE143433333144"),
                new TestCaseId(new GeoPoint(53.870121, 27.550767), "NE121313143121:NE121313143122:NE121313143124:NE121313144131:NE121313144133:NE121313144134"),
                new TestCaseId(new GeoPoint(39.822484, -89.721609), "NW132223332131:NW132223332132:NW132223332134:NW132223332141:NW132223332143:NW132223332144"),
                new TestCaseId(new GeoPoint(-12.173478, -54.291088), "SW131422223231:SW131422223233:SW131422223234:SW131422223432:SW131422223433:SW131422223434"),
                new TestCaseId(new GeoPoint(-9.780389, 29.084406), "SE113424141121:SE113424141123:SE113424141124:SE113424141142:SE113424141143:SE113424141144"),
                new TestCaseId(new GeoPoint(-24.190612, 144.314865), "SE241343331221:SE241343331223:SE241343331224:SE241343331242:SE241343331243:SE241343331244")
        );

        HexGrid grid = new HexGrid(12);
        for (TestCaseId testCase : cases) {
            Assert.assertEquals(testCase.hexId, grid.getZoneAt(testCase.point).getId());
        }
    }

    @Test
    public void getZoneAt_prefix() {
        List<GeoPoint> points = Arrays.asList(
                new GeoPoint(51.631907, 69.882734),
                new GeoPoint(53.870121, 27.550767),
                new GeoPoint(39.822484, -89.721609),
                new GeoPoint(-12.173478, -54.291088),
                new GeoPoint(-9.780389, 29.084406),
                new GeoPoint(-24.190612, 144.314865));
        for (GeoPoint point : points) {
            for (int detalizationLevel = 2; detalizationLevel < 12; detalizationLevel++) {
                HexGrid grid = new HexGrid(detalizationLevel);
                Hex hex = grid.getZoneAt(point);
                HexGrid gridPrev = new HexGrid(detalizationLevel - 1);
                Hex hexPrev = gridPrev.getZoneAt(point);
                List<String> prevTrianglesIds = Arrays.asList(hexPrev.getId().split(":"));
                List<String> trianglesIds = Arrays.asList(hex.getId().split(":"));
                for (String triangleId : trianglesIds) {
                    Assert.assertTrue(prevTrianglesIds.stream().anyMatch(x -> x.equals(triangleId.substring(0, triangleId.length() - 1))));
                }
            }
        }
    }

    @Test
    public void generateZones() throws Exception {
        double delta = 1;
        int radius = 10;
        GeoPoint point0 = new GeoPoint(51.631907, 69.882734);
        List<GeoPoint> points = generateNeighbours(point0, delta, radius);
        printPoints(points);
    }

    private List<GeoPoint> generateNeighbours(GeoPoint point, double delta, int radius) {
        List<GeoPoint> points = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                points.add(new GeoPoint(point.lat + i * delta, point.lon + j * delta));
            }
        }
        return points;
    }

    private void printPoints(List<GeoPoint> points) {
        Grid<Hex> grid = new HexGrid(8);
        Map<String, Hex> hexes = new HashMap<>();
        for (GeoPoint point : points) {
            Hex hex = grid.getZoneAt(point);
            hexes.put(hex.getId(), hex);

        }
        hexes.values().forEach(hex -> {
            System.out.print("[");
            hex.getPolygon().stream()
                    .map(x -> "(" + x.lat + "," + x.lon + ")")
                    .reduce((a, b) -> a + ", " + b)
                    .ifPresent(System.out::println);
            System.out.print("],");
        });
        Hex hex = hexes.values().iterator().next();
        System.out.println(hex.getPolygon().get(0).getDistanceTo(hex.getPolygon().get(1)));
    }

    private class TestCaseId {
        private GeoPoint point;
        private String hexId;
        private TestCaseId(GeoPoint point, String hexId) {
            this.point = point;
            this.hexId = hexId;
        }
    }
}