package com.leitner.wildest.rdr;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Wildest on 11/13/2014.
 */

public class TrkPtInputStream extends InputStream {
    private GpxParser mParser;
    private StringBuilder mBuffer = new StringBuilder();

    public TrkPtInputStream(GpxParser parser) {
        mParser = parser;
    }

    @Override
    public int read() throws IOException {
        if (mBuffer.length() == 0) {
            TrkPt point = mParser.nextTrkPt();
            if (point == null) {
                return -1;
            }
            mBuffer.append(point.getLat() + "," + point.getLon() + "\n");
        }

        int res = mBuffer.charAt(0);
        mBuffer.deleteCharAt(0);
        return res;
    }
}
