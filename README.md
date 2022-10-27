# JVM Tuning Garbage Collection

### Note
- Option is valid if G1 garbage collector is not used
- G1 garbage collector is default option in JRE 11 and can be choosen in JRE 8

| Parameter   |      Remarks   |  
|-------------|:-------------|
| -XX:NewRatio=n |Ratio of Heap between old and young generation, default 2|
| -XX:SurvivorRatio=n|    Default 8, means 1/8 (s0), 1/8(s2) and 3/4 Eden   |  
| -XX:MaxTenuringThreshhold=n | Default 15, the value is indicative, optimization can be still done by JVM |
| -XX:MaxHeapSize | JVM Maz Heap Size (shortcut -Xmx)|
| -XX:InitialHeapSize | JVM initial Heap size (shortcut -Xms)|


## Type of Garbage collector

| Name | JVM Options |
|------|------------|
|Serial|-XX:+UseSerialGC|
|Parallel|-XX:+UseParallelGC|
|Mostly Concurrent|-XX:+UseConcMarkSweepGC, -XX:+UseG1GC|

## Tuning G1 GC

- -XX:ConcGCThreads=n, __Number of threads__ to be used
- -XX:InitiatingHeapOccupancyPercent=n, default 45
- String DeDuplication, frees more space in Heap if it finds duplicate String, only available with G1 GC
```
Use Option: -XX:UseStringDeDuplication
```

## JVM Memory setting for the string Pool

- -XX:+PrintStringTableStatistics
- -XX:StringTableSize=n

### Diagnostics

- -XX:+UnlockDiagnosticVMOptions
- -XX:+PrintFlagsFinal

### PrintCompilation

- -XX:+PrintCompilation

|Time|Order|Flag|Compilation|
|----|-----|----|-----------|
|ms|Order Number|s=Synchronous, !Exception, % code cache |1 - 4, 4 highest

### JVM JIT Compiler

- C1 Level 1 - 3
- C2 Level 4

```
Log Compilation: java -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:+PrintCodeCache
```

#### Code Cache Tuning
- InitialCodeCacheSize=n
- ReservedCodeCacheSize=n
- CodeCacheExpansionSize=n
