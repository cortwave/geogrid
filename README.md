# geogrid

[![Build Status](https://travis-ci.org/cortwave/geogrid.svg?branch=master)](https://travis-ci.org/cortwave/geogrid)

Library for creating hex grid on sphere

### quick start

```java
int detalizationLevel = 12;
Grid<Hex> grid = new HexGrid(detalizationLevel);
double latitude = -35.44;
double longitude = 22.34;
GeoPoint point = new GeoPoint(latitude, longitude);
Hex hex = gride.getZoneAt(point);
List<GeoPoint> polygon = hex.getPolygon();
String zoneId = hex.getId();
```
