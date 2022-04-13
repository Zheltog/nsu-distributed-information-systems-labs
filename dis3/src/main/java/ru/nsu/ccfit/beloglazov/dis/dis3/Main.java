package ru.nsu.ccfit.beloglazov.dis.dis3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
//        CompressedOsmParser.parseWithLoading(
//                ExecuteStrategy.PREPARED_STATEMENT,
//                "RU-NVS.osm.bz2",
//                false,
//                10
//        );
        SpringApplication.run(Main.class, args);
    }
}