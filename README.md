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

## Examples of work

#### Detalization level 5 grid

![detalization level 5](examples/map5.png)

#### Detalization level 16 grid (Manhattan)

![detalization level 16](examples/map16.png)

#### Detalization level 7 (Australia)

![detalization level 17](examples/map7.png)

