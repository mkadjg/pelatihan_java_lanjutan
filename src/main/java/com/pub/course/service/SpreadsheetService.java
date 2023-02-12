package com.pub.course.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SpreadsheetService {
    void generate(OutputStream outputStream) throws IOException;

}
