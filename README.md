# geogrid

[![Build Status](https://travis-ci.org/cortwave/geogrid.svg?branch=master)](https://travis-ci.org/cortwave/geogrid)

Library for creating hex grid on sphere

## Setup

### Gradle (Jitpack dependency)
```gradle
repositories {
    ...
    maven { url "https://jitpack.io" }
}

compile 'com.github.cortwave:geogrid:0.1.0'
```

## Quick start

```java
int detalizationLevel = 12;
Grid<Hex> grid = new HexGrid(detalizationLevel);
double latitude = -35.44;
double longitude = 22.34;
GeoPoint point = new GeoPoint(latitude, longitude);
Hex hex = grid.getZoneAt(point);
List<GeoPoint> polygon = hex.getPolygon();
String zoneId = hex.getId();
```
