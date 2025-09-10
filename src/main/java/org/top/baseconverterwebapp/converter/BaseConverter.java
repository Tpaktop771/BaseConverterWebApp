package org.top.baseconverterwebapp.converter;

import java.util.List;

public interface BaseConverter {
    List<Integer> supportedBases();
    String convert(int fromBase, int toBase, String value);
}
