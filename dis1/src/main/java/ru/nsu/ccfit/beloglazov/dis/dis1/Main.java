package ru.nsu.ccfit.beloglazov.dis.dis1;

public class Main {

    public static void main(String[] args) {
        try {
            CompressedOsmParser cop = new CompressedOsmParser();
            cop.parse("RU-NVS.osm.bz2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}